package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.Attr;
import com.anatkh.product.entity.AttrGroup;
import com.anatkh.product.service.AttrAttrgroupRelationService;
import com.anatkh.product.service.AttrGroupService;
import com.anatkh.product.service.AttrService;
import com.anatkh.product.service.CategoryService;
import com.anatkh.product.vo.AttrGroupRelationVo;
import com.anatkh.product.vo.AttrGroupWithAttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product/attrGroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @GetMapping("{catelogId}/withattr")
    public R getAttrGroupAndAttrsByCatelogId(@PathVariable Long catelogId){
        List<AttrGroupWithAttrVo> attrGroupWithAttrVoList=attrGroupService.getAttrGroupAndAttrsByCatelogId(catelogId);
        return R.ok().put("data",attrGroupWithAttrVoList);
    }
    @PostMapping("attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos){
        attrAttrgroupRelationService.saveBatch(vos);
        return R.ok();
    }

    @GetMapping("list/{catalogId}")
    public R listByCatalogId(@PathVariable Integer catalogId,
                             @RequestParam Map<String,Object> params){

        PageUtils page = attrGroupService.queryPage(params,catalogId);
        return R.ok().put("page",page);
    }

    @GetMapping("info/{attrGroupId}")
    public R infoByAttrGroupId(@PathVariable Integer attrGroupId){
        AttrGroup attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatalogPath(path);
        return R.ok().put("attrGroup",attrGroup);
    }

    @GetMapping("{attrGroupId}/attr/relation")
    public R attrRelation(@PathVariable Long attrGroupId){
        List<Attr> attrList = attrService.getRelationAttr(attrGroupId);
        return R.ok().put("data",attrList);
    }

    @GetMapping("{attrGroupId}/noAttr/relation")
    public R noAttrRelation(@PathVariable Long attrGroupId,
                            @RequestParam Map<String,Object> params){
        PageUtils pageUtils = attrService.getNoRelationAttr(attrGroupId,params);
        return R.ok().put("data",pageUtils);
    }

    @PostMapping("attr/relation/delete")
    public R deleteRelation(@RequestBody List<AttrGroupRelationVo> vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }

}
