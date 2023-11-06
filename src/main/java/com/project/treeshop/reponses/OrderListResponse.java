package com.project.treeshop.reponses;

import com.project.treeshop.models.Order;
import com.project.treeshop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderListResponse {

        private List<Order> orders;
        private int totalPages;
        private long totalOrders;
    }


