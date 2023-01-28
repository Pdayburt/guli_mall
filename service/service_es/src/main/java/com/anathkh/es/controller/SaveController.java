package com.anathkh.es.controller;

import com.anathkh.es.service.ProductSaveService;
import com.anatkh.commonUtil.emum.BizCodeEnum;
import com.anatkh.commonUtil.to.es.SkuESModel;
import com.anatkh.commonUtil.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("search/save")
public class SaveController {

    @Autowired
    private ProductSaveService productSaveService;

    @PostMapping("product")
    public R productStatusUp(@RequestBody List<SkuESModel>skuESModelList){
        Boolean aBoolean = false;
        try {
            aBoolean = productSaveService.productStatusUp(skuESModelList);
        } catch (IOException e) {
            log.error("SaveController商品上架服务出现异常：{}",e);
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if (!aBoolean) return R.ok();
        return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(),BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
    }
}
