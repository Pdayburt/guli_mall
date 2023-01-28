package com.anatkh.ware.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.ware.service.WareSkuService;
import com.anatkh.ware.vo.SkuHasStockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {

    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 查询sku是否有库存
     * @param
     * @return
     */
    @PostMapping("hasStock")
    public R<List<SkuHasStockVo>> getSkuHasStock(@RequestBody List<Long> skuIds){
        List<SkuHasStockVo> voList = wareSkuService.getSkuHasStock(skuIds);
        R<List<SkuHasStockVo>> ok = R.ok();
        ok.setData(voList);
        return ok;
    }

    @PostMapping("list")
    public R list(@RequestParam Map<String,Object> params){
       PageUtils page = wareSkuService.queryPage(params);
       return R.ok().put("page",page);
    }


}
