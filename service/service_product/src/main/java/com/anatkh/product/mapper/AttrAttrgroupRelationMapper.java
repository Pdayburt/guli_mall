package com.anatkh.product.mapper;

import com.anatkh.product.entity.AttrAttrgroupRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author anatkh
* @description 针对表【pms_attr_attrgroup_relation】的数据库操作Mapper
* @createDate 2022-12-13 10:57:17
* @Entity com.anatkh.guliMall.order.entity.AttrAttrgroupRelation
*/
public interface AttrAttrgroupRelationMapper extends BaseMapper<AttrAttrgroupRelation> {

    void deleteBatchRelation(@Param("attrAttrGroupRelations") List<AttrAttrgroupRelation> attrAttrGroupRelations);
}




