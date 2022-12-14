package com.anatkh.guliMall.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.guliMall.ware.entity.Purchase;
import com.anatkh.guliMall.ware.service.PurchaseService;
import com.anatkh.guliMall.ware.mapper.PurchaseMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【wms_purchase(采购信息)】的数据库操作Service实现
* @createDate 2022-12-13 10:59:10
*/
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase>
    implements PurchaseService{

}




