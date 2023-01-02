package com.anatkh.product.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.product.entity.Attr;
import com.anatkh.product.entity.AttrGroup;
import com.anatkh.product.mapper.AttrGroupMapper;
import com.anatkh.product.service.AttrAttrgroupRelationService;
import com.anatkh.product.service.AttrGroupService;
import com.anatkh.product.service.AttrService;
import com.anatkh.product.vo.AttrGroupRelationVo;
import com.anatkh.product.vo.AttrGroupWithAttrVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_attr_group】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup>
    implements AttrGroupService {

    @Autowired
    private AttrService attrService;
    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer catalogId) {
        if (catalogId == 0){
            IPage<AttrGroup> page = baseMapper.selectPage(new Query<AttrGroup>().getPage(params),
                    new QueryWrapper<AttrGroup>());
            return new PageUtils(page);
        }else {
            String key = (String) params.get("key");
            LambdaQueryWrapper<AttrGroup> attrGroupLambdaQueryWrapper = new LambdaQueryWrapper<>();
            attrGroupLambdaQueryWrapper.eq(AttrGroup::getCatelogId,catalogId)
                    .and(item->{
                        item.eq(!StringUtils.isEmpty(key), AttrGroup::getAttrGroupId,key)
                                .or()
                                .like(AttrGroup::getAttrGroupName,key);
                    });
            IPage<AttrGroup> page = page(new Query<AttrGroup>().getPage(params),
                    attrGroupLambdaQueryWrapper);
            return new PageUtils(page);
        }
    }

    @Override
    public List<AttrGroupWithAttrVo> getAttrGroupAndAttrsByCatelogId(Long catelogId) {
        LambdaQueryWrapper<AttrGroup> attrGroupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attrGroupLambdaQueryWrapper
                .eq(AttrGroup::getCatelogId,catelogId);
        List<AttrGroup> attrGroupList = list(attrGroupLambdaQueryWrapper);
        List<AttrGroupWithAttrVo> attrGroupWithAttrVoList = attrGroupList.stream()
                .map(attrGroup -> {
                    AttrGroupWithAttrVo attrGroupWithAttrVo = new AttrGroupWithAttrVo();
                    BeanUtils.copyProperties(attrGroup,attrGroupWithAttrVo);
                    List<Attr> relationAttr = attrService.getRelationAttr(attrGroupWithAttrVo.getAttrGroupId());
                    attrGroupWithAttrVo.setAttrs(relationAttr);
                    return attrGroupWithAttrVo;
                }).collect(Collectors.toList());
        return attrGroupWithAttrVoList;
    }
}




