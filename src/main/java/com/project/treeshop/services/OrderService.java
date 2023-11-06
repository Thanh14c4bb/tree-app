package com.project.treeshop.services;

import com.project.treeshop.dto.OrderDTO;
import com.project.treeshop.models.Order;
import com.project.treeshop.models.OrderStatus;
import com.project.treeshop.models.User;
import com.project.treeshop.repositories.OrderReponsitory;
import com.project.treeshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final UserRepository userRepository;
    private final OrderReponsitory orderReponsitory;


    @Override
    public Order createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Cannot Find User Id "));

        Order newOrder = new Order();
        BeanUtils.copyProperties(orderDTO, newOrder);
        newOrder.setUser(user);
        newOrder.setStatus(OrderStatus.pending);
        newOrder.setOderDate(LocalDateTime.now());
        newOrder.setActive(true);

        return orderReponsitory.save(newOrder);
    }


    @Override
    public Order getOrderById(long id) {
        return orderReponsitory.findById(id)
                .orElseThrow(()->new RuntimeException("cannot Find Order"));

    }

    @Override
    public List<Order> getOrderByUserId(long id) {
        return orderReponsitory.getOrderByUserId(id);
    }

    @Override
    public Page<Order> getAllOrder(Pageable pageable) {
        return orderReponsitory.findAll(pageable);
    }

    @Override
    public Order updateOrderById(long id, Order order) {
        return null;
    }


    @Override
    public void deleteOrderDetailById(long id) {
        Order order = orderReponsitory.findById(id).orElseThrow(()-> new RuntimeException("Not Find Order Id"));
        if(order != null) {
            order.setActive(false);
             orderReponsitory.save(order);
        }

    }
}
