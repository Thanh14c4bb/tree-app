package com.project.treeshop.controllers;

import com.project.treeshop.dto.OrderDTO;
import com.project.treeshop.models.Order;
import com.project.treeshop.reponses.OrderListResponse;
import com.project.treeshop.services.OrderService;
import com.project.treeshop.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody OrderDTO orderDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erroMassege = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(erroMassege);
        }
        Order newOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok("Successfully "+newOrder);
    }
    @GetMapping("/{id}")
    public ResponseEntity <?> getOrderById(@PathVariable long id) {
        try {
            Order checkOrder = orderService.getOrderById(id);
            return ResponseEntity.ok( checkOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/list") //http://localhost:8080/api/v1/orders/list?page=0&size=10
    public ResponseEntity <OrderListResponse> getOrderById(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size)  {
        Pageable pageable = PageRequest.of(page,size, Sort.by("name").ascending());

        Page<Order> allOrder = orderService.getAllOrder(pageable);
        int totalPage = allOrder.getTotalPages();
        long totalOrder = allOrder.getTotalElements();
        List<Order> orderResponses = allOrder.getContent();
        return ResponseEntity.ok(OrderListResponse
                .builder()
                    .orders(orderResponses)
                    .totalOrders(totalOrder)
                    .totalPages(totalPage)
                .build());
    }

    @GetMapping("/orderByUser/{id}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long id) {
        try {
            List<Order> userOrders = orderService.getOrderByUserId(id);
            return ResponseEntity.ok(userOrders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteOrderById (@PathVariable long id) {
        orderService.deleteOrderDetailById(id);
        return ResponseEntity.ok("Delete successfully");
    }
}