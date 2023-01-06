package com.anatkh.ware.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.ware.service.WareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {

    @Autowired
    private WareSkuService wareSkuService;

    @PostMapping("list")
    public R list(@RequestParam Map<String,Object> params){
       PageUtils page = wareSkuService.queryPage(params);
       return R.ok().put("page",page);
    }


}
