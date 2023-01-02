package com.anatkh.product.service;

import com.anatkh.product.entity.AttrAttrgroupRelation;
import com.anatkh.product.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anatkh
* @description 针对表【pms_attr_attrgroup_relation】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelation> {

    void deleteBatchRelation(List<AttrAttrgroupRelation> attrAttrGroupRelations);


    void saveBatch(List<AttrGroupRelationVo> vos);
}
