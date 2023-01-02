package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.Category;
import com.anatkh.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("delete")
    public R delete(@RequestBody List<Integer> catIds){
        categoryService.removeMenusById(catIds);
        return R.ok();
    }

    @PostMapping("save")
    public R save(@RequestBody Category category){
        categoryService.save(category);
        return R.ok();
    }

    @GetMapping("info/{catId}")
    public R info(@PathVariable Integer catId){
        Category category = categoryService.getById(catId);
        return R.ok().put("data",category);
    }

    @PostMapping("update")
    public R update (@RequestBody Category category){
        categoryService.updateCascade(category);
        return R.ok();
    }
}
