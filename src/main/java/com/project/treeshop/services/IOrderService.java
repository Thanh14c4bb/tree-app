package com.project.treeshop.services;

import com.project.treeshop.dto.OrderDTO;
import com.project.treeshop.models.Order;
import com.project.treeshop.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO orderDTO);
    Order getOrderById(long id);

    List<Order> getOrderByUserId(long id);
    Page<Order> getAllOrder(Pageable pageable);

    Order updateOrderById(long id, Order order);

    void deleteOrderDetailById(long id);
}
