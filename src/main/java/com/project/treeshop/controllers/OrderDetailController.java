package com.project.treeshop.controllers;

import com.project.treeshop.models.OrderDetail;
import com.project.treeshop.services.OrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/OrderDetails")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetail orderDetail,
                                               BindingResult result) {
        if (result.hasErrors()) {
            List<String> erroMassege = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(erroMassege);
        }
        OrderDetail newOrderDetail = orderDetailService.createOrderDtail(orderDetail);
        return ResponseEntity.ok("Successfully "+orderDetail);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getorderDetailById(@PathVariable long id) {
        OrderDetail checkorderDetailById =  orderDetailService.getOrderDetailById(id);
        return ResponseEntity.ok(checkorderDetailById);
    }
}
