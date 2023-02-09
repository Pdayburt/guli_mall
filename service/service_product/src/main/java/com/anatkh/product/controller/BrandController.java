package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.commonUtil.valid.AddGroup;
import com.anatkh.product.entity.Brand;
import com.anatkh.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("update")
    public R update(@RequestBody Brand brand){
        brandService.updateDetail(brand);
        return R.ok();
    }

    @PostMapping("save")
    public R save(@Validated({AddGroup.class}) @RequestBody Brand brand /*, BindingResult result*/){

        brandService.save(brand);
        return R.ok();
    }

    @GetMapping("list")
    public R list(@RequestParam Map<String,Object> params){
        PageUtils page = brandService.queryPage(params);
        return R.ok().put("page",page);
    }
}
