package com.anatkh.product.mapper;

import com.anatkh.product.entity.Attr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anatkh
* @description 针对表【pms_attr】的数据库操作Mapper
* @createDate 2022-12-13 10:57:17
* @Entity com.anatkh.guliMall.order.entity.Attr
*/
public interface AttrMapper extends BaseMapper<Attr> {

    List<Long> selectSearchAttrs(@Param("attrIdList") List<Long> attrIdList);
}




