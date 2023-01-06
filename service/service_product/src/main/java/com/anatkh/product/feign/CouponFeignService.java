package com.anatkh.product.feign;

import com.anatkh.commonUtil.to.SkuReductionTo;
import com.anatkh.commonUtil.to.SpuBoundTo;
import com.anatkh.commonUtil.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-coupon")
public interface CouponFeignService {

    @PostMapping("coupon/spubounds/save")
    R saveSpuBounds(@RequestBody SpuBoundTo spuBoundTo);

    @PostMapping("coupon/skufullreduction/saveInfo")
    R saveSkuReduction(@RequestBody SkuReductionTo skuReductionTo);


}
