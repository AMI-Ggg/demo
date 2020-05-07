package com.Pipi.demo.Service.Impl;

import com.Pipi.demo.Service.CommonService;
import com.Pipi.demo.Service.UserService;
import com.Pipi.demo.Utils.DateTool;
import com.Pipi.demo.Utils.RedisUtils;
import com.Pipi.demo.Utils.ValidationUtil;
import com.Pipi.demo.constant.Constants;
import com.Pipi.demo.dao.UserMapper;
import com.Pipi.demo.dto.request.loginRequest;
import com.Pipi.demo.dto.response.LoginResponse;
import com.Pipi.demo.entity.User;
import com.Pipi.demo.entity.UserExample;
import com.Pipi.demo.entity.custom.CUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    @Override
    public LoginResponse login(loginRequest request) {
        LoginResponse response = new LoginResponse();
        // 参数校验
        ValidationUtil.ValidResult vr = ValidationUtil.validateBean(request);
        if (vr.hasErrors()) {
            response.setErrMsg(vr.getErrors());
            response.setErrCode(Integer.toString(Constants.ErrCode.PARAM_CHECK_ILLEGAL));
            return response;
        }

        //验证账号密码
        String account = request.getUserAccount();
        String password = request.getPassword();
        UserExample example = new UserExample();
        example.createCriteria().andUserAccountEqualTo(account);
        List<User> userList = userMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(userList)) {
            User user = userList.get(Constants.CONSTANT_ZERO_INT);
            if (StringUtils.equals(password, user.getUserPassword())) {
                String token = DigestUtils.md5Hex("login" + account + DateTool.getDateTime(new Date()));
                try {
                    redisUtils.sSetAndTime(account, Constants.REDIS_HALFHOUR, token);
                    response.setToken(user.getUserAccount() + Constants.TOKEN_SPLIT_SIGN + token);
                } catch (Exception e) {
                    logger.info("token生成失败", e.getMessage());
                }
            } else {
                response.setErrCode(Constants.PARAM_CHECK_ILLEGAL);
                response.setErrMsg("密码错误");
            }
        } else {
            response.setErrCode(Constants.PARAM_CHECK_ILLEGAL);
            response.setErrMsg("不存在该用户");
        }
        return response;
    }

    @Override
    public CUser checkLogin(String token) {
        String userAccount = token.split(Constants.TOKEN_SPLIT_SIGN)[Constants.CONSTANT_ZERO_INT];
        String originToken = token.split(Constants.TOKEN_SPLIT_SIGN)[Constants.CONSTANT_ONE_INT];
        if (redisUtils.isSMember(userAccount, originToken)) {
            redisUtils.expire(userAccount, Constants.REDIS_HALFHOUR);
            return userService.getUserInfoByAccount(userAccount);
        }
        return null;
    }
}
