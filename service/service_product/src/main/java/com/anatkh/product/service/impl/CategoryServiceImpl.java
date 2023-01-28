package com.anatkh.product.service.impl;

import com.anatkh.product.entity.Category;
import com.anatkh.product.mapper.CategoryMapper;
import com.anatkh.product.service.CategoryBrandRelationService;
import com.anatkh.product.service.CategoryService;
import com.anatkh.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_category】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public List<Category> listWithTree() {

        List<Category> categoryList = baseMapper.selectList(null);
        List<Category> finalList = categoryList.stream()
                .filter(menu -> menu.getParentCid() == 0)
                .map(menu -> {
                    menu.setChildren(getChildren(menu, categoryList));
                    return menu;
                }).sorted((m1, m2) -> {
                    return (m1.getSort() == null ? 0 : m1.getSort()) - (m2.getSort() == null ? 0 : m2.getSort());
                }).collect(Collectors.toList());
        return finalList;
    }

    @Override
    public void removeMenusById(List<Integer> catIds) {
        //TODO 检查菜单是否被引用
        baseMapper.deleteBatchIds(catIds);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId,path);
        Collections.reverse(parentPath);
        return parentPath.toArray(new Long[parentPath.size()]);
    }

    @Transactional
    @Override
    public void updateCascade(Category category) {
        baseMapper.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());
    }

    @Override
    public List<Category> LevelOneCategory() {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper
                .eq(Category::getParentCid,0);
        List<Category> categoryList = baseMapper.selectList(categoryLambdaQueryWrapper);
        return categoryList;
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        List<Category> levelOneCategoryList = LevelOneCategory();
        Map<Long, List<Catelog2Vo>> map = levelOneCategoryList.stream().collect(Collectors.toMap(Category::getCatId, category -> {
            LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
            categoryLambdaQueryWrapper.eq(Category::getParentCid, category.getCatId());
            List<Category> categoryList = baseMapper.selectList(categoryLambdaQueryWrapper);
            List<Catelog2Vo> catelog2VoList = null;
            if (categoryList != null) {
                catelog2VoList = categoryList.stream().map(item -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo();
                    catelog2Vo.setCatalog1Id(category.getCatId().toString());
                    catelog2Vo.setId(item.getCatId().toString());
                    catelog2Vo.setName(item.getName());
                    LambdaQueryWrapper<Category> categoryLambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                    categoryLambdaQueryWrapper1
                            .eq(Category::getParentCid, item.getCatId());
                    List<Category> categoryList1 = baseMapper.selectList(categoryLambdaQueryWrapper1);
                    List<Catelog2Vo.Catelog3Vo> catelog3VoList = null;
                    if (categoryList1 != null) {
                        catelog3VoList = categoryList1.stream().map(category1 -> {
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo();
                            catelog3Vo.setCatalog2Id(item.getCatId().toString());
                            catelog3Vo.setName(category1.getName());
                            catelog3Vo.setId(category1.getCatId().toString());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                    }
                    catelog2Vo.setCatalog3List(catelog3VoList);
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return catelog2VoList;
        }));
        Map<String, List<Catelog2Vo>> collect = map.entrySet().stream()
                .collect(Collectors.toMap(e -> String.valueOf(e.getKey()), Map.Entry::getValue));
        return collect;
    }

    private List<Long> findParentPath(Long catelogId, List<Long> path) {
        path.add(catelogId);
        Category category = baseMapper.selectById(catelogId);
        if (category.getParentCid() != 0){
            findParentPath(category.getParentCid(),path);
        }
        return path;
    }


    private List<Category> getChildren(Category category, List<Category> categoryList) {
        List<Category> childrenList = categoryList.stream()
                .filter(menu -> menu.getParentCid().equals(category.getCatId()))
                .map(menu -> {
                    menu.setChildren(getChildren(menu, categoryList));
                    return menu;
                }).sorted((m1, m2) -> {
                    return (m1.getSort() == null ? 0 : m1.getSort()) - (m2.getSort() == null ? 0 : m2.getSort());
                }).collect(Collectors.toList());
        return childrenList;
    }
}




