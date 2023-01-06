package com.anatkh.coupon.controller;

import com.anatkh.commonUtil.utils.R;
import com.anatkh.coupon.entity.SpuBounds;
import com.anatkh.coupon.service.SpuBoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coupon/spubounds")
public class SpuBoundsController {
    @Autowired
    private SpuBoundsService spuBoundsService;

    @PostMapping("save")
    public R save(@RequestBody SpuBounds spuBounds){
        spuBoundsService.save(spuBounds);
        return R.ok();
    }
}
