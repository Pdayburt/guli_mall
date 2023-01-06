package com.anatkh.ware.service.impl;

import com.anatkh.commonUtil.constant.WareConstant;
import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.ware.entity.Purchase;
import com.anatkh.ware.entity.PurchaseDetail;
import com.anatkh.ware.mapper.PurchaseMapper;
import com.anatkh.ware.service.PurchaseDetailService;
import com.anatkh.ware.service.WareSkuService;
import com.anatkh.ware.vo.MergeVo;
import com.anatkh.ware.vo.PurchaseDoneVo;
import com.anatkh.ware.vo.PurchaseItemDoneVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.ware.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
* @author anatkh
* @description 针对表【wms_purchase(采购信息)】的数据库操作Service实现
* @createDate 2022-12-13 10:59:10
*/
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{

    @Autowired
    private PurchaseDetailService purchaseDetailService;
    @Autowired
    private WareSkuService wareSkuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        LambdaQueryWrapper<Purchase> purchaseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        purchaseLambdaQueryWrapper
                .eq(Purchase::getStatus,0)
                .or()
                .eq(Purchase::getStatus,1);
        return null;
    }

    @Transactional
    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if (purchaseId == null){
            Purchase purchase = new Purchase();
            purchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            baseMapper.insert(purchase);
            purchaseId=purchase.getId();
        }
        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetail> purchaseDetailList = items.stream().map(i -> {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setId(i);
            purchaseDetail.setPurchaseId(finalPurchaseId);
            purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return purchaseDetail;
        }).collect(Collectors.toList());
        purchaseDetailService.updateBatchById(purchaseDetailList);
    }

    /**
     *
     * @param ids 采购单id
     */
    @Override
    public void received(List<Long> ids) {
        //1，确认当前采购单是新建或者是已分配
        List<Purchase> purchaseList = ids.stream().map(i -> {
            Purchase purchase = baseMapper.selectById(i);
            return purchase;
        }).filter(purchase -> {
            if (purchase.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() || purchase.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                return true;
            }
            return false;
        }).map(purchase -> {
             purchase.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
             return purchase;
        }).collect(Collectors.toList());
        //2，改变采购单的状态
        updateBatchById(purchaseList);
        //3，改变采购项目的状态
        purchaseList.forEach(purchase -> {
            List<PurchaseDetail> purchaseDetailList = purchaseDetailService.listDetailByPurchaseId(purchase.getId());
            List<PurchaseDetail> purchaseDetails = purchaseDetailList.stream().map(purchaseDetail -> {
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.BUY.getCode());
                return purchaseDetail;
            }).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(purchaseDetails);
        });


    }

    @Transactional
    @Override
    public void done(PurchaseDoneVo purchaseDoneVo) {
        //1，改变采购单的状态
        Long id = purchaseDoneVo.getId();
        //2，改变采购项的状态
        AtomicReference<Boolean> flag = new AtomicReference<>(true);
        List<PurchaseItemDoneVo> purchaseItemDoneVoList = purchaseDoneVo.getItems();
        List<PurchaseDetail> purchaseItemDoneVos = new ArrayList<>();

        purchaseItemDoneVoList.forEach(purchaseItemDoneVo -> {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            if (purchaseItemDoneVo.getStatus() == WareConstant.PurchaseDetailStatusEnum.ERROR.getCode()){
                flag.set(false);
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.ERROR.getCode());
            }else {
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                //3，将成功采购的进行入库
                PurchaseDetail purchaseDetailServiceById = purchaseDetailService.getById(purchaseItemDoneVo.getItemId());
                wareSkuService.addStock(purchaseDetailServiceById.getSkuId(),purchaseDetailServiceById.getWareId(),purchaseDetailServiceById.getSkuNum());
            }
            purchaseDetail.setId(purchaseItemDoneVo.getItemId());
            purchaseItemDoneVos.add(purchaseDetail);
        });
        purchaseDetailService.updateBatchById(purchaseItemDoneVos);
        Purchase purchase = new Purchase();
        purchase.setId(id);
        purchase.setStatus(flag.get()?WareConstant.PurchaseStatusEnum.FINISH.getCode() : WareConstant.PurchaseStatusEnum.ERROR.getCode());
        updateById(purchase);


    }


}




