package com.anatkh.product.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.product.entity.SpuInfo;
import com.anatkh.product.vo.SpuSaveVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【pms_spu_info(spu)】的数据库操作Service
* @createDate 2022-12-13 10:57:17
*/
public interface SpuInfoService extends IService<SpuInfo> {

    void saveSpuInfo(SpuSaveVo spuSaveVo);

    PageUtils queryPageByCondition(Map<String, Object> params);

    /**
     * 尚品上架
     * @param spuId
     */
    void spuUP(Long spuId);
}
