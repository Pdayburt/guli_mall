package com.anatkh.member.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-coupon")
public interface CouponFeignService {


}
