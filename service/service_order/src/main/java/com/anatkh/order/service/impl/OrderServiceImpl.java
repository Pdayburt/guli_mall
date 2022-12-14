package com.anatkh.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.order.entity.Order;
import com.anatkh.order.service.OrderService;
import com.anatkh.order.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【oms_order】的数据库操作Service实现
* @createDate 2022-12-13 10:56:05
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




