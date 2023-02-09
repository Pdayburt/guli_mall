package com.anatkh.product.web;

import com.anatkh.product.entity.Category;
import com.anatkh.product.service.CategoryService;
import com.anatkh.product.vo.Catelog2Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/","/index.html"})
    public String  indexPage(Model  model){
        List<Category> categoryList = categoryService.LevelOneCategory();
        model.addAttribute("categoryList",categoryList);
        return "index";
    }
    @ResponseBody
    @GetMapping("index/catalog.json")
    public Map<String,List<Catelog2Vo>> getCatalogJson(){
        Map<String,List<Catelog2Vo>> map = categoryService.getCatalogJson();
        return map;
    }
}
