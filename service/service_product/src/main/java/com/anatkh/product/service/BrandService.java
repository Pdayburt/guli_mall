package com.anatkh.product.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.product.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_brand(Ʒ)】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface BrandService extends IService<Brand> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(Brand brand);
}
