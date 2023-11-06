package com.project.treeshop.services;

import com.project.treeshop.models.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail createOrderDtail(OrderDetail orderDetail);
    OrderDetail getOrderDetailById(long id);
    List<OrderDetail> getAllOrderDetail();
    OrderDetail updateOrderDetail(long id, OrderDetail orderDetail);
    void deleteOrderDetailById(long id);

}
