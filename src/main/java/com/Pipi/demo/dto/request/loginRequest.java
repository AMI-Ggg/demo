package com.Pipi.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class loginRequest {

    @NotBlank
    private String userAccount;

    @NotBlank
    private String password;
}
