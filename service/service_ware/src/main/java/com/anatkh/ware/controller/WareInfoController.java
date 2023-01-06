package com.anatkh.ware.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.ware.service.WareInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("ware/wareinfo")
public class WareInfoController {

    @Autowired
    private WareInfoService wareInfoService;

    @GetMapping("list")
    public R list(@RequestParam Map<String,Object> params){
        PageUtils page = wareInfoService.queryPageByCondition(params);
        return R.ok().put("page",page);
    }

}
