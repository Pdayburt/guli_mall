package com.anatkh.ware.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.ware.entity.PurchaseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【wms_purchase_detail】的数据库操作Service
* @createDate 2022-12-13 10:59:10
*/
public interface PurchaseDetailService extends IService<PurchaseDetail> {

    PageUtils queryPage(Map<String, Object> params);

    List<PurchaseDetail> listDetailByPurchaseId(Long id);
}
