package com.anatkh.guliMall.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.guliMall.ware.entity.WareInfo;
import com.anatkh.guliMall.ware.service.WareInfoService;
import com.anatkh.guliMall.ware.mapper.WareInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service实现
* @createDate 2022-12-13 10:59:10
*/
@Service
public class WareInfoServiceImpl extends ServiceImpl<WareInfoMapper, WareInfo>
    implements WareInfoService{

}




