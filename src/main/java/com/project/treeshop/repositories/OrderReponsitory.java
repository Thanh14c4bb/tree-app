package com.project.treeshop.repositories;

import com.project.treeshop.models.Order;
import com.project.treeshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderReponsitory extends JpaRepository<Order, Long> {

    List<Order> getOrderByUserId(long id);

}
