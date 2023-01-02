package com.anatkh.product.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.product.entity.Brand;
import com.anatkh.product.entity.CategoryBrandRelation;
import com.anatkh.product.mapper.BrandMapper;
import com.anatkh.product.service.BrandService;
import com.anatkh.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_brand(Ʒ)】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
    implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        LambdaQueryWrapper<Brand> brandLambdaQueryWrapper = new LambdaQueryWrapper<>();
        brandLambdaQueryWrapper
                .eq(!StringUtils.isEmpty(key),Brand::getBrandId,key)
                .or()
                .like(!StringUtils.isEmpty(key),Brand::getName,key);
        IPage<Brand> brandIPage = baseMapper.selectPage(new Query<Brand>().getPage(params), brandLambdaQueryWrapper);
        return new PageUtils(brandIPage);
    }

    @Transactional
    @Override
    public void updateDetail(Brand brand) {
        //保证冗余数据的一致性
        baseMapper.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
            categoryBrandRelationService.updateBrand(brand.getBrandId(),brand.getName());
        }
    }
}




