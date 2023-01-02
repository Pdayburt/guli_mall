package com.anatkh.product.service.impl;

import com.anatkh.product.entity.Category;
import com.anatkh.product.mapper.CategoryMapper;
import com.anatkh.product.service.CategoryBrandRelationService;
import com.anatkh.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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




