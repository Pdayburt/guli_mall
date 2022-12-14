package com.anatkh.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.order.entity.OrderOperateHistory;
import com.anatkh.order.service.OrderOperateHistoryService;
import com.anatkh.order.mapper.OrderOperateHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【oms_order_operate_history】的数据库操作Service实现
* @createDate 2022-12-13 10:56:05
*/
@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper, OrderOperateHistory>
    implements OrderOperateHistoryService{

}




