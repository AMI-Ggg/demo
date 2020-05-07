package com.Pipi.demo.dto.request;

import lombok.Data;
import javax.validation.constraints.Pattern;

@Data
public class PasswordRequest extends BaseRequest{
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9]{7,}$", message = "密码最低8位,允许字母及数字")
    private String newPassword;

}
