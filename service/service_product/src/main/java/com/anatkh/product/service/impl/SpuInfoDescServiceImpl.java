package com.anatkh.product.service.impl;

import com.anatkh.product.entity.SpuInfoDesc;
import com.anatkh.product.mapper.SpuInfoDescMapper;
import com.anatkh.product.service.SpuInfoDescService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【pms_spu_info_desc(spu)】的数据库操作Service实现
* @createDate 2022-12-13 10:57:17
*/
@Service
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescMapper, SpuInfoDesc>
    implements SpuInfoDescService {

    @Override
    public void saveSpuInfoDesc(SpuInfoDesc spuInfoDesc) {
        baseMapper.insert(spuInfoDesc);
    }
}




