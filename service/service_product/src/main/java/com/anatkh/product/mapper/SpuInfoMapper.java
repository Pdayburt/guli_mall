package com.anatkh.product.mapper;

import com.anatkh.product.entity.SpuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author anatkh
* @description 针对表【pms_spu_info(spu)】的数据库操作Mapper
* @createDate 2022-12-13 10:57:17
* @Entity com.anatkh.guliMall.order.entity.SpuInfo
*/
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {

    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") Integer code);
}




