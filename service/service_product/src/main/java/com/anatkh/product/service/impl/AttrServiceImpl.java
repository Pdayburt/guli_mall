package com.anatkh.product.service.impl;

import com.anatkh.commonUtil.constant.ProductConstant;
import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.product.entity.*;
import com.anatkh.product.mapper.AttrMapper;
import com.anatkh.product.service.AttrAttrgroupRelationService;
import com.anatkh.product.service.AttrGroupService;
import com.anatkh.product.service.AttrService;
import com.anatkh.product.service.CategoryService;
import com.anatkh.product.vo.AttrGroupRelationVo;
import com.anatkh.product.vo.AttrResponseVo;
import com.anatkh.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_attr】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr>
    implements AttrService {

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;
    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;
    @Transactional
    @Override
    public void saveAttrVo(AttrVo attrVo) {
        Attr attr = new Attr();
        BeanUtils.copyProperties(attrVo,attr);
        baseMapper.insert(attr);
        if (attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attrVo.getAttrGroupId() != null){
            AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
            attrAttrgroupRelation.setAttrGroupId(attrVo.getAttrGroupId());
            attrAttrgroupRelation.setAttrId(attrVo.getAttrId());
            attrAttrgroupRelationService.save(attrAttrgroupRelation);
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId, String attrType) {
        String key = (String) params.get("key");
        LambdaQueryWrapper<Attr> attrLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attrLambdaQueryWrapper
                .eq(Attr::getAttrType,"base".equalsIgnoreCase(attrType)?ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode():ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode())
                .eq(catelogId != 0,Attr::getCatelogId,catelogId)
                .and(!StringUtils.isEmpty(key),item->{
                            item.eq(Attr::getAttrId,key)
                                    .or()
                                    .like(Attr::getAttrName,key);
                        });
        IPage<Attr> page = baseMapper.selectPage(new Query<Attr>().getPage(params), attrLambdaQueryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        List<AttrResponseVo> responseVos = page.getRecords().stream()
                .map(attr -> {
                    AttrResponseVo attrResponseVo = new AttrResponseVo();
                    BeanUtils.copyProperties(attr, attrResponseVo);
                    if ("base".equalsIgnoreCase(attrType)) {
                        AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.
                                getOne(new LambdaQueryWrapper<AttrAttrgroupRelation>().eq(AttrAttrgroupRelation::getAttrId, attr.getAttrId()));
                        if (attrAttrgroupRelation != null) {
                            AttrGroup attrGroup = attrGroupService.getById(attrAttrgroupRelation.getAttrGroupId());
                            attrResponseVo.setGroupName(attrGroup.getAttrGroupName());
                        }
                    }
                    Category category = categoryService.getById(attr.getCatelogId());
                    if (category != null) {
                        attrResponseVo.setCatelogName(category.getName());
                    }
                    return attrResponseVo;
                }).collect(Collectors.toList());
        pageUtils.setList(responseVos);
        return pageUtils;
    }

    @Transactional
    @Override
    public AttrResponseVo getAttrInfo(Long attrId) {
        AttrResponseVo attrResponseVo = new AttrResponseVo();
        Attr attr = baseMapper.selectById(attrId);
        BeanUtils.copyProperties(attr,attrResponseVo);
        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            LambdaQueryWrapper<AttrAttrgroupRelation> categoryBrandRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
            categoryBrandRelationLambdaQueryWrapper
                    .eq(AttrAttrgroupRelation::getAttrId,attrId);
            //设置分组信息
            AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationService.getOne(categoryBrandRelationLambdaQueryWrapper);
            if (attrAttrgroupRelation != null && attrAttrgroupRelation.getAttrGroupId() != null){
                attrResponseVo.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
                AttrGroup attrGroup = attrGroupService.getById(attrAttrgroupRelation.getAttrGroupId());
                if (attrGroup != null) attrResponseVo.setCatelogName(attrGroup.getAttrGroupName());
            }
        }
        //设置分类信息
        Long catelogId = attr.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        attrResponseVo.setCatelogPath(catelogPath);
        Category category = categoryService.getById(catelogId);
        if (category != null) attrResponseVo.setCatelogName(category.getName());
        return attrResponseVo;
    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attrVo) {
        Attr attr = new Attr();
        BeanUtils.copyProperties(attrVo,attr);
        baseMapper.updateById(attr);
        if (attrVo.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            LambdaUpdateWrapper<AttrAttrgroupRelation> attrAttrgroupRelationLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            attrAttrgroupRelationLambdaUpdateWrapper
                    .eq(AttrAttrgroupRelation::getAttrId,attrVo.getAttrId());
            AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
            attrAttrgroupRelation.setAttrGroupId(attrVo.getAttrGroupId());
            attrAttrgroupRelation.setAttrId(attrVo.getAttrId());
            int count = attrAttrgroupRelationService.count(attrAttrgroupRelationLambdaUpdateWrapper);
            if (count > 0){
                attrAttrgroupRelationService.update(attrAttrgroupRelation,attrAttrgroupRelationLambdaUpdateWrapper);
            }else {
                attrAttrgroupRelationService.save(attrAttrgroupRelation);
            }
        }
    }

    @Override
    public List<Attr> getRelationAttr(Long attrGroupId) {
        LambdaQueryWrapper<AttrAttrgroupRelation> attrAttrgroupRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attrAttrgroupRelationLambdaQueryWrapper
                .eq(AttrAttrgroupRelation::getAttrGroupId,attrGroupId);
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.list(attrAttrgroupRelationLambdaQueryWrapper);
        List<Long> attrList = list.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        if (attrList==null || attrList.size() == 0) return null;
        List<Attr> attrListRes = baseMapper.selectBatchIds(attrList);
        return attrListRes;
    }

    @Override
    public void deleteRelation(List<AttrGroupRelationVo> vos) {
        List<AttrAttrgroupRelation> attrAttrGroupRelations = vos.stream().map(vo -> {
            AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
            BeanUtils.copyProperties(vo, attrAttrgroupRelation);
            return attrAttrgroupRelation;
        }).collect(Collectors.toList());
        attrAttrgroupRelationService.deleteBatchRelation(attrAttrGroupRelations);
    }

    @Override
    public PageUtils getNoRelationAttr(Long attrGroupId, Map<String, Object> params) {
        AttrGroup attrGroup= attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        LambdaQueryWrapper<AttrGroup> attrGroupLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attrGroupLambdaQueryWrapper
                .eq(AttrGroup::getCatelogId, catelogId);
        List<AttrGroup> group = attrGroupService.list(attrGroupLambdaQueryWrapper);
        List<Long> attrGroupIds = group.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());

        LambdaQueryWrapper<AttrAttrgroupRelation> attrAttrgroupRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attrAttrgroupRelationLambdaQueryWrapper
                .in(AttrAttrgroupRelation::getAttrGroupId,attrGroupIds);
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.list(attrAttrgroupRelationLambdaQueryWrapper);
        List<Long> attrIds = list.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());

        LambdaQueryWrapper<Attr> attrLambdaQueryWrapper = new LambdaQueryWrapper<>();
        attrLambdaQueryWrapper
                .eq(Attr::getAttrType,ProductConstant.AttrEnum.ATTR_TYPE_BASE)
                .eq(Attr::getCatelogId,attrGroupId)
                .notIn(attrIds != null && attrIds.size()>0,Attr::getAttrId,attrIds)
                .and(!StringUtils.isEmpty((String)params.get("key")),attr->{
                    attr.eq(Attr::getAttrId,(String)params.get("key"))
                            .or()
                            .like(Attr::getAttrName,(String)params.get("key"));
                });
        IPage<Attr> page = baseMapper.selectPage(new Query<Attr>().getPage(params), attrLambdaQueryWrapper);
        return new PageUtils(page);
    }
}




