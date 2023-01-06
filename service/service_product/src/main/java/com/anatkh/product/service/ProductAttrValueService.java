package com.anatkh.product.service;

import com.anatkh.product.entity.ProductAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anatkh
* @description 针对表【pms_product_attr_value(spu)】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface ProductAttrValueService extends IService<ProductAttrValue> {

    void saveProductAttr(List<ProductAttrValue> productAttrValueList);

    List<ProductAttrValue> baseAttrListForSpu(Long spuId);

    void updateSpuAttr(Long spuId, List<ProductAttrValue> productAttrValueList);
}
