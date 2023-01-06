package com.anatkh.ware.feign;

import com.anatkh.commonUtil.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("infrastructure-gateway")
public interface ProductFeignService {

    /**
     * 通过网关转发请求，不通过也可以
     * @param skuId
     * @return
     */
    @GetMapping("api/product/skuinfo/info/{skuId}")
    public R infoBySkuId(@PathVariable Long skuId);
}
