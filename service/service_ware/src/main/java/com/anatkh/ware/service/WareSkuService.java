package com.anatkh.ware.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.ware.entity.WareSku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【wms_ware_sku(商品库存)】的数据库操作Service
* @createDate 2022-12-13 10:59:10
*/
public interface WareSkuService extends IService<WareSku> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);
}
