package com.anatkh.ware.mapper;

import com.anatkh.ware.entity.WareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author anatkh
* @description 针对表【wms_ware_sku(商品库存)】的数据库操作Mapper
* @createDate 2022-12-13 10:59:10
* @Entity com.anatkh.guliMall.ware.entity.WareSku
*/
public interface WareSkuMapper extends BaseMapper<WareSku> {


    void addStock(@Param("skuId") Long skuId,@Param("wareId") Long wareId,@Param("skuNum") Integer skuNum);
}




