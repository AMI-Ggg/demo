package com.Pipi.demo.controller;

import com.Pipi.demo.Service.CommonService;
import com.Pipi.demo.Service.UserService;
import com.Pipi.demo.Utils.DateTool;
import com.Pipi.demo.constant.Constants;
import com.Pipi.demo.dto.request.MobilePhoneRequest;
import com.Pipi.demo.dto.request.NickRequest;
import com.Pipi.demo.dto.request.PasswordRequest;
import com.Pipi.demo.dto.request.UserAddRequest;
import com.Pipi.demo.dto.response.BaseResponse;
import com.Pipi.demo.entity.custom.CUser;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;


@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private  UserService userService;

    @Autowired
    private CommonService commonService;

    /**
     * 新增用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse addUser(@RequestBody UserAddRequest request) {
        BaseResponse response;
        response = userService.addUser(request);
        logger.info("调用接口addUser，入参={}，返回={}", JSON.toJSONString(request), JSON.toJSONString(response));
        return response;
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/alterPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse alterPassword(@RequestHeader String token, @RequestBody PasswordRequest request) {
        request.setUser(commonService.checkLogin(token));
        BaseResponse response;
        response = userService.alterPassword(request);
        return response;
    }

    /**
     * 修改昵称
     * @param request
     * @return
     */
    @RequestMapping(value = "/alterNick", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse alterNick(@RequestHeader String token, @RequestBody NickRequest request) {
        request.setUser(commonService.checkLogin(token));
        BaseResponse response;
        response = userService.alterNick(request);
        return response;
    }

    /**
     * 修改绑定手机
     * @param request
     * @return
     */
    @RequestMapping(value = "/alterMobilePhone", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse alterMobilePhone(@RequestHeader String token, @RequestBody MobilePhoneRequest request) {
        request.setUser(commonService.checkLogin(token));
        BaseResponse response;
        response = userService.alterMobilePhone(request);
        return response;
    }

    /**
     * 导出用户列表
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportUserList", method = RequestMethod.GET)
    @ResponseBody
    public void exportUserList(@RequestHeader String token, HttpServletResponse response) {
        String fileName = "用户表" + DateTool.getDateToLong(new Date());
        try {
            if (commonService.checkLogin(token) == null) {
                response.sendError(Constants.CONSTANT_ZERO_INT, "用户无权限，请重新登录");
                return;
            }
            response.reset();
            response.setContentType("application/binary;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
            OutputStream outputStream = response.getOutputStream();
            userService.exportUserList().write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
