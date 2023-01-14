package com.anatkh.product.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.product.entity.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_sku_info(sku)】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface SkuInfoService extends IService<SkuInfo> {

    void saveSkuInfo(SkuInfo skuInfo);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void up(Long spuId);
}
