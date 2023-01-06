package com.anatkh.product.controller;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.entity.ProductAttrValue;
import com.anatkh.product.service.AttrService;
import com.anatkh.product.service.ProductAttrValueService;
import com.anatkh.product.vo.AttrResponseVo;
import com.anatkh.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    @Autowired
    private ProductAttrValueService productAttrValueService;

    @PostMapping("update/{spuId}")
    public R updateSpuAttr(@PathVariable Long spuId,
                           @RequestBody List<ProductAttrValue> productAttrValueList){
        productAttrValueService.updateSpuAttr(spuId,productAttrValueList);
        return R.ok();
    }

    @GetMapping("base/listforspu/{spuId}")
    public R baseAttrListForSpu(@PathVariable Long spuId){
        List<ProductAttrValue> productAttrValueList =  productAttrValueService.baseAttrListForSpu(spuId);
        return R.ok().put("data",productAttrValueList);
    }
    @PostMapping("save")
    public R save(@RequestBody AttrVo attrVo){
        attrService.saveAttrVo(attrVo);
        return R.ok();
    }

    @GetMapping("{attrType}/list/{catelogId}")
    public R baseList(@PathVariable String attrType,
                      @PathVariable Long catelogId,
                      @RequestParam Map<String,Object> params){
        PageUtils page = attrService.queryPage(params,catelogId,attrType);
        return R.ok().put("page",page);
    }

    @GetMapping("info/{attrId}")
    public R infoByAttrId(@PathVariable Long attrId){
        AttrResponseVo attrResponseVo = attrService.getAttrInfo(attrId);
        return R.ok().put("attr",attrResponseVo);
    }

    @PostMapping("update")
    public R update(@RequestBody AttrVo attrVo){
        attrService.updateAttr(attrVo);
        return R.ok();
    }
}
