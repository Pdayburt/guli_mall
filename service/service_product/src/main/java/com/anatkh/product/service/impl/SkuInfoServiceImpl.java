package com.anatkh.product.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.product.entity.SkuInfo;
import com.anatkh.product.mapper.SkuInfoMapper;
import com.anatkh.product.service.SkuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_sku_info(sku)】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
    implements SkuInfoService {

    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        baseMapper.insert(skuInfo);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        String key = (String) params.get("key");
        String brandId = (String) params.get("brandId");
        String catelogId = (String) params.get("catelogId");
        String min = (String) params.get("min");
        String max = (String) params.get("max");
        LambdaQueryWrapper<SkuInfo> skuInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        skuInfoLambdaQueryWrapper
                .and(!StringUtils.isEmpty(key),wrapper->{
                    wrapper.eq(SkuInfo::getSkuId,key)
                            .or()
                            .like(SkuInfo::getSkuName,key);
                })
                .eq(!StringUtils.isEmpty(brandId) && !"0".equalsIgnoreCase(brandId),SkuInfo::getBrandId,brandId)
                .eq(!StringUtils.isEmpty(catelogId) && !"0".equalsIgnoreCase(catelogId),SkuInfo::getCatalogId,catelogId)
                .ge(!StringUtils.isEmpty(min) && new BigDecimal(min).compareTo(new BigDecimal("0"))==1,SkuInfo::getPrice,min)
                .le(!StringUtils.isEmpty(max) && new BigDecimal(max).compareTo(new BigDecimal("0"))==1,SkuInfo::getPrice,max);
        IPage<SkuInfo> skuInfoIPage = baseMapper.selectPage(new Query<SkuInfo>().getPage(params), skuInfoLambdaQueryWrapper);
        return new PageUtils(skuInfoIPage);
    }

    @Override
    public void up(Long spuId) {

    }
}




