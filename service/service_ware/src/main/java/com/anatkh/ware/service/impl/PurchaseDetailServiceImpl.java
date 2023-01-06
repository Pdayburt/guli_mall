package com.anatkh.ware.service.impl;

import com.anatkh.commonUtil.utils.PageUtils;
import com.anatkh.commonUtil.utils.Query;
import com.anatkh.ware.entity.PurchaseDetail;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.ware.service.PurchaseDetailService;
import com.anatkh.ware.mapper.PurchaseDetailMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
* @author anatkh
* @description 针对表【wms_purchase_detail】的数据库操作Service实现
* @createDate 2022-12-13 10:59:10
*/
@Service
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper, PurchaseDetail>
    implements PurchaseDetailService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String status = (String) params.get("status");
        String wareId = (String) params.get("wareId");
        LambdaQueryWrapper<PurchaseDetail> purchaseDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        purchaseDetailLambdaQueryWrapper
                .and(!StringUtils.isEmpty(key),purchaseDetail->{
                    purchaseDetail.eq(PurchaseDetail::getPurchaseId,key)
                            .or()
                            .eq(PurchaseDetail::getSkuId,key);
                })
                .eq(!StringUtils.isEmpty(status),PurchaseDetail::getStatus,status)
                .eq(!StringUtils.isEmpty(wareId),PurchaseDetail::getWareId,wareId);
        IPage<PurchaseDetail> purchaseDetailIPage = baseMapper.selectPage(new Query<PurchaseDetail>().getPage(params), purchaseDetailLambdaQueryWrapper);
        return new PageUtils(purchaseDetailIPage);
    }

    @Override
    public List<PurchaseDetail> listDetailByPurchaseId(Long id) {
        LambdaQueryWrapper<PurchaseDetail> purchaseDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
        purchaseDetailLambdaQueryWrapper
                .eq(PurchaseDetail::getPurchaseId,id);
        return baseMapper.selectList(purchaseDetailLambdaQueryWrapper);
    }
}




