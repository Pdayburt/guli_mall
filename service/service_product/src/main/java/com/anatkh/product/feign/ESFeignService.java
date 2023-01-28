package com.anatkh.product.feign;

import com.anatkh.commonUtil.to.es.SkuESModel;
import com.anatkh.commonUtil.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("service-es")
public interface ESFeignService {
    @PostMapping("search/save/product")
    public R productStatusUp(@RequestBody List<SkuESModel> skuESModelList);
}
