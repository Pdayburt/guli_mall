package com.anatkh.ware.service;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.ware.entity.Purchase;
import com.anatkh.ware.vo.MergeVo;
import com.anatkh.ware.vo.PurchaseDoneVo;
import com.anatkh.ware.vo.PurchaseItemDoneVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【wms_purchase(采购信息)】的数据库操作Service
* @createDate 2022-12-13 10:59:10
*/
public interface PurchaseService extends IService<Purchase> {

    PageUtils queryPage(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);


    void done(PurchaseDoneVo purchaseDoneVo);
}
