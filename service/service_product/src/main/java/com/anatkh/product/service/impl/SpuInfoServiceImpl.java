package com.anatkh.product.service.impl;

import com.anatkh.commonUtil.constant.ProductConstant;
import com.anatkh.commonUtil.to.SkuReductionTo;
import com.anatkh.commonUtil.to.SpuBoundTo;
import com.anatkh.commonUtil.to.es.SkuESModel;
import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.*;
import com.anatkh.product.entity.Attr;
import com.anatkh.product.feign.CouponFeignService;
import com.anatkh.product.feign.ESFeignService;
import com.anatkh.product.feign.WareFeignService;
import com.anatkh.product.mapper.SpuInfoMapper;
import com.anatkh.product.service.*;
import com.anatkh.product.vo.*;
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
import java.util.ArrayList;
import java.util.HashSet;
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

    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private WareFeignService wareFeignService;
    @Autowired
    private ESFeignService esFeignService;
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

    @Override
    public void spuUP(Long spuId) {
        /**
         * 查出当前spuId下的所有sku信息，包含品牌名字。
         */
        // 查询当前sku所有可以被查询的规格属性
        List<ProductAttrValue> productAttrValueList = productAttrValueService.baseAttrListForSpu(spuId);
        List<Long> attrIdList = productAttrValueList.stream().map(productAttrValue -> {
            return productAttrValue.getAttrId();
        }).collect(Collectors.toList());
        List<Long> searchAttrIds = attrService.selectSearchAttrs(attrIdList);
        HashSet<Long> idSet = new HashSet<>(searchAttrIds);
        List<SkuESModel.Attrs> attrsList = productAttrValueList.stream().filter(productAttrValue -> {
            return idSet.contains(productAttrValue.getAttrId());
        }).map(productAttrValue -> {
            SkuESModel.Attrs attrs = new SkuESModel.Attrs();
            BeanUtils.copyProperties(productAttrValue, attrs);
            return attrs;
        }).collect(Collectors.toList());
        List<SkuInfo> skuInfoList = skuInfoService.getSkusBySpuId(spuId);
        List<Long> skuIdList = skuInfoList.stream().map(SkuInfo::getSkuId).collect(Collectors.toList());
        //远程调用 库存系统查询是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            R<List<SkuHasStockVo>> skuHasStock = wareFeignService.getSkuHasStock(skuIdList);
            List<SkuHasStockVo> data = skuHasStock.getData();
            stockMap = data.stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::isHasStock));
        }catch (Exception e){
            log.error("wareFeignService 远程调用出现异常：原因 {}",e);
        }

        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuESModel> skuESModelList = skuInfoList.stream().map(skuInfo -> {
            SkuESModel skuESModel = new SkuESModel();
            BeanUtils.copyProperties(skuInfo,skuESModel);
            skuESModel.setSkuPrice(skuInfo.getPrice());
            skuESModel.setSkuImg(skuInfo.getSkuDefaultImg());
            if (finalStockMap == null) skuESModel.setHasStock(true);
            skuESModel.setHasStock(finalStockMap.get(skuInfo.getSkuId()));
            skuESModel.setHotScore(0L);
            //查询 品牌和分类的名字信息
            Brand brand = brandService.getById(skuESModel.getBrandId());
            skuESModel.setBrandName(brand.getName());
            skuESModel.setBrandImg(brand.getLogo());
            Category category = categoryService.getById(skuESModel.getCatalogId());
            skuESModel.setCatalogName(category.getName());
            //设置检索属性
            skuESModel.setAttrs(attrsList);
            return skuESModel;
        }).collect(Collectors.toList());
//        将数据发送给es进行保存
        R r = esFeignService.productStatusUp(skuESModelList);
        if (r.getCode() == 0){
//            上架成功，修改spu的状态
            baseMapper.updateSpuStatus(spuId, ProductConstant.StateEnum.SPU_UP.getCode());
        }else {
            //重复调用的问题，接口幂等性：重试机制
        }
    }
}




