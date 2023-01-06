package com.anatkh.ware.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.ware.service.PurchaseService;
import com.anatkh.ware.vo.MergeVo;
import com.anatkh.ware.vo.PurchaseDoneVo;
import com.anatkh.ware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("done")
    public R done(@RequestBody PurchaseDoneVo purchaseDoneVo){
        purchaseService.done(purchaseDoneVo);
        return R.ok();
    }
    @PostMapping("received")
    public R received(@RequestBody List<Long> ids){
        purchaseService.received(ids);
        return R.ok();
    }

    @PostMapping("merge")
    public R merge(@RequestBody MergeVo mergeVo){
        purchaseService.mergePurchase(mergeVo);
        return R.ok();

    }

    @GetMapping("unreceive/list")
    public R unreceiveList(@RequestParam Map<String,Object> params){
        PageUtils page = purchaseService.queryPage(params);
        return R.ok().put("page",page);
    }
}
