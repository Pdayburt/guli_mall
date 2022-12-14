package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.Category;
import com.anatkh.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("list/tree")
    public R list(){
        List<Category> list = categoryService.listWithTree();
        return R.ok().put("data", list);
    }
}
