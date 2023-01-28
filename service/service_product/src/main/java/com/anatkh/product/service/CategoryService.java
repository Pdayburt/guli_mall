package com.anatkh.product.service;

import com.anatkh.product.entity.Category;
import com.anatkh.product.vo.Catelog2Vo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_category】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface CategoryService extends IService<Category> {

    List<Category> listWithTree();

    void removeMenusById(List<Integer> catIds);

    Long[] findCatelogPath(Long catelogId);

    void updateCascade(Category category);

    List<Category> LevelOneCategory();

    Map<String, List<Catelog2Vo>> getCatalogJson();
}
