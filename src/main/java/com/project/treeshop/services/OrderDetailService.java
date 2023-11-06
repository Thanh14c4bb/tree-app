package com.project.treeshop.services;

import com.project.treeshop.models.Order;
import com.project.treeshop.models.OrderDetail;
import com.project.treeshop.models.Product;
import com.project.treeshop.repositories.OrderDetailRepository;
import com.project.treeshop.repositories.OrderReponsitory;
import com.project.treeshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService{
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderReponsitory orderReponsitory;
    @Override
    public OrderDetail createOrderDtail(OrderDetail orderDetail) {
//        Tìm order ID có chưa:

        Order order = orderReponsitory.findById(orderDetail.getOrder().getId())
                .orElseThrow(()->new RuntimeException("Cannot Find Order ID"));
//        tim id product co ton tai ko
        Product product = productRepository.findById(orderDetail.getProduct().getId())
                .orElseThrow(()-> new RuntimeException("Cannot Find Product ID"));
        OrderDetail orderDetail1 = OrderDetail
                .builder()
                .order(order)
                .product(product)
                .numberOfOrder(orderDetail.getNumberOfOrder())
                .total(orderDetail.getTotal())
                .build();
        return orderDetailRepository.save(orderDetail1);

    }

    @Override
    public OrderDetail getOrderDetailById(long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cannot Find Order Detail ID"));
    }

    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return null;
    }

    @Override
    public OrderDetail updateOrderDetail(long id, OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void deleteOrderDetailById(long id) {

    }
}
