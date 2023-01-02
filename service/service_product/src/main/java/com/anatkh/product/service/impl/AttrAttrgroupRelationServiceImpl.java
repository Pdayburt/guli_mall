package com.anatkh.product.service.impl;

import com.anatkh.product.entity.AttrAttrgroupRelation;
import com.anatkh.product.mapper.AttrAttrgroupRelationMapper;
import com.anatkh.product.service.AttrAttrgroupRelationService;
import com.anatkh.product.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_attr_attrgroup_relation】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationMapper, AttrAttrgroupRelation>
    implements AttrAttrgroupRelationService {


    @Override
    public void deleteBatchRelation(List<AttrAttrgroupRelation> attrAttrGroupRelations) {
        baseMapper.deleteBatchRelation(attrAttrGroupRelations);
    }

    @Override
    public void saveBatch(List<AttrGroupRelationVo> vos) {
        List<AttrAttrgroupRelation> attrAttrgroupRelations = vos.stream().map(item -> {
            AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
            BeanUtils.copyProperties(item, attrAttrgroupRelation);
            return attrAttrgroupRelation;
        }).collect(Collectors.toList());
        saveBatch(attrAttrgroupRelations);
    }


}




