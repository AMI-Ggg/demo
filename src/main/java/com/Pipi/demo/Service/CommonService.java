package com.Pipi.demo.Service;

import com.Pipi.demo.dto.request.loginRequest;
import com.Pipi.demo.dto.response.LoginResponse;
import com.Pipi.demo.entity.custom.CUser;

public interface CommonService {

    /**
     * 用户登录
     */
    LoginResponse login(loginRequest request);

    /**
     * 验证登录状态
     */
    CUser checkLogin(String token);
}
