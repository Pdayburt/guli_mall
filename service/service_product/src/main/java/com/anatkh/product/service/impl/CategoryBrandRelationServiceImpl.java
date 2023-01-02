package com.anatkh.product.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.product.entity.Brand;
import com.anatkh.product.entity.Category;
import com.anatkh.product.entity.CategoryBrandRelation;
import com.anatkh.product.mapper.CategoryBrandRelationMapper;
import com.anatkh.product.service.BrandService;
import com.anatkh.product.service.CategoryBrandRelationService;
import com.anatkh.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_category_brand_relation(Ʒ)】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationMapper, CategoryBrandRelation>
    implements CategoryBrandRelationService {

    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;


    @Override
    public List<CategoryBrandRelation> findByBrandId(Long brandId) {
        LambdaQueryWrapper<CategoryBrandRelation> categoryBrandRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryBrandRelationLambdaQueryWrapper
                .eq(!StringUtils.isEmpty(brandId),CategoryBrandRelation::getBrandId,brandId);
        return baseMapper.selectList(categoryBrandRelationLambdaQueryWrapper);
    }

    @Override
    public void saveDeatil(CategoryBrandRelation categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        Brand brand = brandService.getById(brandId);
        Category category = categoryService.getById(catelogId);
        categoryBrandRelation.setBrandName(brand.getName());
        categoryBrandRelation.setCatelogName(category.getName());
        baseMapper.insert(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
        categoryBrandRelation.setBrandId(brandId);
        categoryBrandRelation.setBrandName(name);
        LambdaUpdateWrapper<CategoryBrandRelation> categoryBrandRelationLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        categoryBrandRelationLambdaUpdateWrapper
                .eq(CategoryBrandRelation::getBrandId,brandId);
        baseMapper.update(categoryBrandRelation,categoryBrandRelationLambdaUpdateWrapper);
    }

    @Override
    public void updateCategory(Long catId, String name) {
        CategoryBrandRelation categoryBrandRelation = new CategoryBrandRelation();
        categoryBrandRelation.setCatelogId(catId);
        categoryBrandRelation.setCatelogName(name);
        LambdaUpdateWrapper<CategoryBrandRelation> categoryBrandRelationLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        categoryBrandRelationLambdaUpdateWrapper
                .eq(!StringUtils.isEmpty(catId),CategoryBrandRelation::getCatelogId,catId);
        baseMapper.update(categoryBrandRelation,categoryBrandRelationLambdaUpdateWrapper);
    }

    @Override
    public List<Brand> getBrandsByCatId(Long catId) {
        LambdaQueryWrapper<CategoryBrandRelation> categoryBrandRelationLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryBrandRelationLambdaQueryWrapper
                .eq(CategoryBrandRelation::getCatelogId,catId);
        List<CategoryBrandRelation> categoryBrandRelationList = baseMapper.selectList(categoryBrandRelationLambdaQueryWrapper);
        List<Long> brandIds = categoryBrandRelationList.stream()
                .map(categoryBrandRelation -> {
                    return categoryBrandRelation.getBrandId();
                }).collect(Collectors.toList());
        List<Brand> brandList = brandService.listByIds(brandIds);
        return brandList;
    }
}




