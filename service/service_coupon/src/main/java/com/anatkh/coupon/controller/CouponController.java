package com.anatkh.coupon.controller;




import com.anatkh.commonUtil.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope(proxyMode = ScopedProxyMode.DEFAULT)
@RestController
@RequestMapping("coupon")
public class CouponController {

    @Value("${xuchao.name}")
    private String age;

    @RequestMapping("getAge")
    private R getAge(){
        System.out.println("age===="+age);
        return R.ok().put("age", age);
    }
}
