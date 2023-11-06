package com.project.treeshop.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.treeshop.models.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("user")
    private User user;
}
