package com.Pipi.demo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class UserAddRequest extends BaseRequest {

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9]+$", message = "用户名为字母开头，允许数字和字母")
    private String userAccount;

    @Pattern(regexp = "^[A-Za-z0-9]{7,}$", message = "密码最低8位,允许字母及数字")
    private String password;

    @NotBlank(message = "昵称不能为空")
    private String Nick;

    @Pattern(regexp = "^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\\d{8}$", message = "手机号格式错误")
    private String mobilePhone;

}
