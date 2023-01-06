package com.anatkh.product.service.impl;

import com.anatkh.product.entity.ProductAttrValue;
import com.anatkh.product.mapper.ProductAttrValueMapper;
import com.anatkh.product.service.ProductAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_product_attr_value(spu)】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueMapper, ProductAttrValue>
    implements ProductAttrValueService {

    @Override
    public void saveProductAttr(List<ProductAttrValue> productAttrValueList) {
        saveBatch(productAttrValueList);
    }

    @Override
    public List<ProductAttrValue> baseAttrListForSpu(Long spuId) {
        LambdaQueryWrapper<ProductAttrValue> productAttrValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productAttrValueLambdaQueryWrapper
                .eq(ProductAttrValue::getSpuId,spuId);
        return baseMapper.selectList(productAttrValueLambdaQueryWrapper);
    }

    @Transactional
    @Override
    public void updateSpuAttr(Long spuId, List<ProductAttrValue> productAttrValueList) {
        //1，删除这个spuId之前对应的所有属性
        LambdaQueryWrapper<ProductAttrValue> productAttrValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productAttrValueLambdaQueryWrapper
                .eq(ProductAttrValue::getSpuId,spuId);
        baseMapper.delete(productAttrValueLambdaQueryWrapper);
        List<ProductAttrValue> productAttrValues = productAttrValueList.stream().map(productAttrValue -> {
            productAttrValue.setSpuId(spuId);
            return productAttrValue;
        }).collect(Collectors.toList());
        saveBatch(productAttrValues);
    }
}




