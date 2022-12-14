package com.anatkh.product.service.impl;

import com.anatkh.product.entity.Category;
import com.anatkh.product.mapper.CategoryMapper;
import com.anatkh.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author anatkh
* @description 针对表【pms_category】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService {

    @Override
    public List<Category> listWithTree() {

        List<Category> categories = baseMapper.selectList(null);

        return categories;
    }
}




