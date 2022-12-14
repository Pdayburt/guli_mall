package com.anatkh.product.service;

import com.anatkh.product.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anatkh
* @description 针对表【pms_category】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface CategoryService extends IService<Category> {

    List<Category> listWithTree();
}
