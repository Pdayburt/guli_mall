package com.anatkh.coupon.service;

import com.anatkh.commonUtil.to.SkuReductionTo;
import com.anatkh.coupon.entity.SkuFullReduction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author anatkh
* @description 针对表【sms_sku_full_reduction】的数据库操作Service
* @createDate 2022-12-13 10:53:04
*/
public interface SkuFullReductionService extends IService<SkuFullReduction> {

    void saveSkuReductionTo(SkuReductionTo skuReductionTo);
}
