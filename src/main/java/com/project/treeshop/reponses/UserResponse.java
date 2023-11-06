package com.project.treeshop.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.treeshop.models.Role;
import com.project.treeshop.models.User;
import lombok.Builder;

import java.util.Date;
@Builder

public class UserResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phone_Number;

    @JsonProperty("address")
    private String address;

    @JsonProperty("is_active")
    private boolean active;


    @JsonProperty("role")
    private Role role;
    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phone_Number(String.valueOf(user.getPhoneNumber()))
                .active(user.isActive())
                .role(user.getRole())
                .build();
    }
}
