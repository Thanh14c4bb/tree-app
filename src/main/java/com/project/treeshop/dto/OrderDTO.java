package com.project.treeshop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotEmpty(message = "PhoneNumber cannot be empty")
    private String phoneNumber;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    private String note;

    private float totalMoney;

    private String status;

    private LocalDateTime orderDate;

    private boolean active;

    private Long userId;
}
