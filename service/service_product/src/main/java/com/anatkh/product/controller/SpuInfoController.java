package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.service.SpuInfoService;
import com.anatkh.product.vo.SpuSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {

    @Autowired
    private SpuInfoService spuInfoService;

    @PostMapping("list")
    public R list(@RequestParam Map<String,Object> params){
        PageUtils page = spuInfoService.queryPageByCondition(params);
        return R.ok().put("page",page);
    }
    @PostMapping("save")
    public R save(@RequestBody SpuSaveVo spuSaveVo){
        spuInfoService.saveSpuInfo(spuSaveVo);
        return R.ok();
    }
}
