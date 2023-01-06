package com.anatkh.ware.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.ware.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("ware/purchasedetail")
public class PurchaseDetailController {
    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @GetMapping("list")
    public R list(@RequestParam Map<String,Object> params){
        PageUtils page = purchaseDetailService.queryPage(params);
        return R.ok().put("page",page);

    }

}
