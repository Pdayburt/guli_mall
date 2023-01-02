package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.Brand;
import com.anatkh.product.entity.CategoryBrandRelation;
import com.anatkh.product.service.CategoryBrandRelationService;
import com.anatkh.product.vo.AttrGroupRelationVo;
import com.anatkh.product.vo.BrandVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @RequestMapping("brands/list")
    public R relationBrandsList(@RequestParam(required = true) Long catId){
        List<Brand> brandList = categoryBrandRelationService.getBrandsByCatId(catId);
        List<BrandVo> brandVoList = brandList.stream().map(brand -> {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandName(brand.getName());
            brandVo.setBrandId(brandVo.getBrandId());
            return brandVo;
        }).collect(Collectors.toList());
        return R.ok().put("data",brandVoList);
    }
    @GetMapping("catalog/list")
    public R catalogList(Long brandId){
        List<CategoryBrandRelation> data= categoryBrandRelationService.findByBrandId(brandId);
        return R.ok().put("data",data);
    }

    @PostMapping("save")
    public R save(@RequestBody CategoryBrandRelation categoryBrandRelation){
        categoryBrandRelationService.saveDeatil(categoryBrandRelation);
        return R.ok();
    }


}
