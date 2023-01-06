package com.anatkh.product.service.impl;

import com.anatkh.commonUtil.to.SkuReductionTo;
import com.anatkh.commonUtil.to.SpuBoundTo;
import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.*;
import com.anatkh.product.feign.CouponFeignService;
import com.anatkh.product.mapper.SpuInfoMapper;
import com.anatkh.product.service.*;
import com.anatkh.product.vo.*;
import com.anatkh.product.vo.Attr;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_spu_info(spu)】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
    implements SpuInfoService {

    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private SpuImagesService spuImagesService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private CouponFeignService couponFeignService;
    @Transactional
    @Override
    public void saveSpuInfo(SpuSaveVo spuSaveVo) {

//         1,保存spu基本信息 pms_spu_info
        SpuInfo spuInfo = new SpuInfo();
        BeanUtils.copyProperties(spuSaveVo,spuInfo);
        baseMapper.insert(spuInfo);
//         2,保存spu的描述图片 pms_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();
        SpuInfoDesc spuInfoDesc = new SpuInfoDesc();
        spuInfoDesc.setSpuId(spuInfo.getId());
        spuInfoDesc.setDecript(String.join(",",decript));
        spuInfoDescService.saveSpuInfoDesc(spuInfoDesc);
//         3,保存spu的图片集 pms_spu_images
        List<String> images = spuSaveVo.getImages();
        spuImagesService.saveImages(spuInfo.getId(),images);
//         4,保存spu的规格参数 pms_product_attr_value
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        List<ProductAttrValue> productAttrValueList = baseAttrs.stream().map(baseAttr -> {
            ProductAttrValue productAttrValue = new ProductAttrValue();
            productAttrValue.setAttrId(baseAttr.getAttrId());
            productAttrValue.setAttrName(attrService.getById(baseAttr.getAttrId()).getAttrName());
            productAttrValue.setAttrValue(baseAttr.getAttrValues());
            productAttrValue.setQuickShow(baseAttr.getShowDesc());
            productAttrValue.setSpuId(spuInfo.getId());
            return productAttrValue;
        }).collect(Collectors.toList());
        productAttrValueService.saveProductAttr(productAttrValueList);
//         保存spu的积分信息sms_spu_bounds
        List<Skus> skus = spuSaveVo.getSkus();
        if (skus != null && skus.size()>0){
            skus.forEach(sku->{
                String defaultImg = "";
                for (Images image : sku.getImages()) {
                    if (image.getDefaultImg() == 1){
                        defaultImg=image.getImgUrl();
                    }
                }
//                5、保存spu的积分信息 sms_spu_bounds
                Bounds bounds = spuSaveVo.getBounds();
                SpuBoundTo spuBoundTo = new SpuBoundTo();
                BeanUtils.copyProperties(bounds,spuBoundTo);
                spuBoundTo.setSpuId(spuInfo.getId());
                R r1 = couponFeignService.saveSpuBounds(spuBoundTo);
                if (r1.getCode() != 0) log.error("远程保存spu积分信息失败！");
//         5,保存当前spu对应的所有sku信息
                //          5.1 sku的基本信息 pms_sku_info
                SkuInfo skuInfo = new SkuInfo();
                BeanUtils.copyProperties(sku,skuInfo);
                skuInfo.setBrandId(spuInfo.getBrandId());
                skuInfo.setCatalogId(spuInfo.getCatalogId());
                skuInfo.setSaleCount(0L);
                skuInfo.setSpuId(spuInfo.getId());
                skuInfo.setSkuDefaultImg(defaultImg);
                skuInfoService.saveSkuInfo(skuInfo);

                Long skuId = skuInfo.getSkuId();

                List<SkuImages> skuImagesList = sku.getImages().stream().map(image -> {
                    SkuImages skuImages = new SkuImages();
                    skuImages.setSkuId(skuId);
                    skuImages.setImgUrl(image.getImgUrl());
                    skuImages.setDefaultImg(image.getDefaultImg());
                    return skuImages;
                }).filter(image->{
                            return !StringUtils.isEmpty(image.getImgUrl());
                        })
                        .collect(Collectors.toList());
                //          5.2 sku的图片信息 pms_sku_image
                skuImagesService.saveBatch(skuImagesList);
                //          5.3 sku的销售属性信息 pms_sku_sale_attr_value
                List<SkuSaleAttrValue> skuSaleAttrValueList = sku.getAttr().stream().map(attr -> {
                    SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
                    BeanUtils.copyProperties(attr, skuSaleAttrValue);
                    skuSaleAttrValue.setSkuId(skuId);
                    return skuSaleAttrValue;
                }).collect(Collectors.toList());
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueList);
//          5.4 sku的优惠满减信息 sms_sku_ladder/sms_sku_full_reduction/sms_member_price
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(sku,skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if (skuReductionTo.getFullCount() > 0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0"))==1){
                    R r2 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if (r2.getCode() != 0) log.error("远程保存sku优惠信息失败！");
                }

            });
        }








    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        String key = (String) params.get("key");
        String status = (String) params.get("status");
        String brandId = (String) params.get("brandId");
        String catelogId = (String) params.get("catelogId");
        LambdaQueryWrapper<SpuInfo> spuInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spuInfoLambdaQueryWrapper
                .and(wrapper->{
                    wrapper.eq(!StringUtils.isEmpty(key),SpuInfo::getId,key)
                            .or()
                            .eq(!StringUtils.isEmpty(key),SpuInfo::getSpuName,key);
                }).eq(!StringUtils.isEmpty(status),SpuInfo::getPublishStatus,status)
                        .eq(!StringUtils.isEmpty(brandId),SpuInfo::getBrandId,brandId)
                                .eq(!StringUtils.isEmpty(catelogId),SpuInfo::getCatalogId,catelogId);
        IPage<SpuInfo> page = page(new Query<SpuInfo>().getPage(params), spuInfoLambdaQueryWrapper);
        return new PageUtils(page);
    }
}




