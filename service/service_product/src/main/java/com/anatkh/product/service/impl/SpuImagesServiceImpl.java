package com.anatkh.product.service.impl;

import com.anatkh.product.mapper.SpuImagesMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.product.entity.SpuImages;
import com.anatkh.product.service.SpuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【pms_spu_images(spuͼƬ)】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesMapper, SpuImages>
    implements SpuImagesService{

    @Override
    public void saveImages(Long id, List<String> images) {
        if (images==null || images.size() ==0) {
        }else {
            List<SpuImages> spuImagesList = images.stream().map(image -> {
                SpuImages spuImages = new SpuImages();
                spuImages.setImgUrl(image);
                spuImages.setSpuId(id);
                return spuImages;
            }).collect(Collectors.toList());
            saveBatch(spuImagesList);
        }
    }
}




