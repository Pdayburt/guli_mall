package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.SkuInfo;
import com.anatkh.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;
    @PostMapping("list")
    public R list(@RequestParam Map<String,Object> params){
        PageUtils page = skuInfoService.queryPageByCondition(params);
        return R.ok().put("page",page);
    }
    @GetMapping("info/{skuId}")
    public R infoBySkuId(@PathVariable Long skuId){
        SkuInfo skuInfo = skuInfoService.getById(skuId);
        return R.ok().put("skuInfo",skuInfo);
    }
}
