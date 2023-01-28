package com.anatkh.product.feign;

import com.anatkh.commonUtil.utils.R;
import com.anatkh.product.vo.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("service-ware")
public interface WareFeignService {

    @PostMapping("ware/waresku/hasStock")
    R<List<SkuHasStockVo>> getSkuHasStock(@RequestBody List<Long> skuIds);


}
