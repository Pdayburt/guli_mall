package com.anatkh.product.service;

import com.anatkh.product.entity.SpuImages;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author anatkh
* @description 针对表【pms_spu_images(spuͼƬ)】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface SpuImagesService extends IService<SpuImages> {

    void saveImages(Long id, List<String> images);
}
