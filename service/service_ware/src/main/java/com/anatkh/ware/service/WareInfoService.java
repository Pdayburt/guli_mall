package com.anatkh.ware.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.ware.entity.WareInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author anatkh
* @description 针对表【wms_ware_info(仓库信息)】的数据库操作Service
* @createDate 2022-12-13 10:59:10
*/
public interface WareInfoService extends IService<WareInfo> {

    PageUtils queryPageByCondition(Map<String, Object> params);
}
