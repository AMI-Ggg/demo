package com.Pipi.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NickRequest extends BaseRequest{

    @NotBlank(message = "昵称不能为空")
    private String Nick;
}
