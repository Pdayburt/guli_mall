package com.anatkh.product.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.product.entity.Brand;
import com.anatkh.product.entity.CategoryBrandRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_category_brand_relation(Ʒ)】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface CategoryBrandRelationService extends IService<CategoryBrandRelation> {


    List<CategoryBrandRelation> findByBrandId(Long brandId);

    void saveDeatil(CategoryBrandRelation categoryBrandRelation);

    void updateBrand(Long brandId, String name);

    void updateCategory(Long catId, String name);

    List<Brand> getBrandsByCatId(Long catId);
}
