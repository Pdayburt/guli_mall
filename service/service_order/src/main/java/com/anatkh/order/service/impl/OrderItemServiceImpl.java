package com.anatkh.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.order.entity.OrderItem;
import com.anatkh.order.service.OrderItemService;
import com.anatkh.order.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【oms_order_item】的数据库操作Service实现
* @createDate 2022-12-13 10:56:05
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
    implements OrderItemService{

}




