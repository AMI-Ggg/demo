package com.Pipi.demo.controller;

import com.Pipi.demo.Service.CommonService;
import com.Pipi.demo.dto.request.loginRequest;
import com.Pipi.demo.dto.response.LoginResponse;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {

    private Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;


    /**
     * 用户登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse login(@RequestBody loginRequest request) {
        LoginResponse response;
        response = commonService.login(request);
        logger.info("调用接口login，入参={}，返回={}", JSON.toJSONString(request), JSON.toJSONString(response));
        return response;
    }

}
