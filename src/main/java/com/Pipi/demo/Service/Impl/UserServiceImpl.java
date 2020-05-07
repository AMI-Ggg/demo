package com.Pipi.demo.Service.Impl;

import com.Pipi.demo.Service.UserService;
import com.Pipi.demo.Utils.ExcelUtils;
import com.Pipi.demo.Utils.ValidationUtil;
import com.Pipi.demo.constant.Constants;
import com.Pipi.demo.dao.UserMapper;
import com.Pipi.demo.dto.request.MobilePhoneRequest;
import com.Pipi.demo.dto.request.NickRequest;
import com.Pipi.demo.dto.request.PasswordRequest;
import com.Pipi.demo.dto.request.UserAddRequest;
import com.Pipi.demo.dto.response.BaseResponse;
import com.Pipi.demo.entity.User;
import com.Pipi.demo.entity.UserExample;
import com.Pipi.demo.entity.custom.CUser;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @author guohl4
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse addUser(UserAddRequest request) {
        BaseResponse response = new BaseResponse();
        // 参数校验
        ValidationUtil.ValidResult vr = ValidationUtil.validateBean(request);
        if (vr.hasErrors()) {
            response.setErrMsg(vr.getErrors());
            response.setErrCode(Integer.toString(Constants.ErrCode.PARAM_CHECK_ILLEGAL));
            return response;
        }
        try {
            User user = new User();
            user.setUserAccount(request.getUserAccount());
            user.setUserPassword(request.getPassword());
            user.setNick(request.getNick());
            user.setMobilePhone(request.getMobilePhone());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userMapper.insertSelective(user);
        } catch (Exception e) {
            response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
            if (e.getClass() == DuplicateKeyException.class) {
                if (e.getMessage().contains("'user_account'")) {
                    response.setErrMsg("用户已存在");
                } else if (e.getMessage().contains("'nick_name'")) {
                    response.setErrMsg("昵称已存在");
                } else if (e.getMessage().contains("'mobile_phone'")) {
                    response.setErrMsg("该手机号已绑定另一用户");
                }
            } else {
                response.setErrMsg(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public BaseResponse alterPassword(PasswordRequest request) {
        BaseResponse response = new BaseResponse();
        // 参数校验
        ValidationUtil.ValidResult vr = ValidationUtil.validateBean(request);
        if (vr.hasErrors()) {
            response.setErrMsg(vr.getErrors());
            response.setErrCode(Integer.toString(Constants.ErrCode.PARAM_CHECK_ILLEGAL));
            return response;
        }
        UserExample example = new UserExample();
        example.createCriteria().andUserAccountEqualTo(request.getUser().getUserAccount());
        try {
            User user = userMapper.selectByExample(example).get(0);
            if (!StringUtils.equals(user.getUserPassword(), request.getPassword())) {
                response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
                response.setErrMsg("原密码不正确");
                return response;
            } else if (StringUtils.equals(user.getUserPassword(), request.getNewPassword())) {
                response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
                response.setErrMsg("新旧密码相同");
            }
            user.setUserPassword(request.getNewPassword());
            user.setUpdateTime(new Date());
            userMapper.updateByExample(user, example);
        } catch (Exception e) {
            response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
            response.setErrMsg(e.getMessage());
        }
        return response;
}

    @Override
    public BaseResponse alterNick(NickRequest request) {
        BaseResponse response = new BaseResponse();
        // 参数校验
        ValidationUtil.ValidResult vr = ValidationUtil.validateBean(request);
        if (vr.hasErrors()) {
            response.setErrMsg(vr.getErrors());
            response.setErrCode(Integer.toString(Constants.ErrCode.PARAM_CHECK_ILLEGAL));
            return response;
        }
        try {
            User user = new User();
            user.setNick(request.getNick());
            user.setUpdateTime(new Date());
            UserExample example = new UserExample();
            example.createCriteria().andUserAccountEqualTo(request.getUser().getUserAccount());
            userMapper.updateByExampleSelective(user, example);
        } catch (Exception e) {
            if (e.getClass() == DuplicateKeyException.class) {
                response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
                response.setErrMsg("该昵称已存在");
            } else {
                response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
                response.setErrMsg(e.getMessage());
            }
        }
        return response;
    }

    @Override
    public BaseResponse alterMobilePhone(MobilePhoneRequest request) {
        BaseResponse response = new BaseResponse();
        // 参数校验
        ValidationUtil.ValidResult vr = ValidationUtil.validateBean(request);
        if (vr.hasErrors()) {
            response.setErrMsg(vr.getErrors());
            response.setErrCode(Integer.toString(Constants.ErrCode.PARAM_CHECK_ILLEGAL));
            return response;
        }
        try {
            UserExample example = new UserExample();
            example.createCriteria().andUserAccountEqualTo(request.getUser().getUserAccount());
            User user = userMapper.selectByExample(example).get(0);
            if(!StringUtils.equals(user.getMobilePhone(), request.getUser().getMobilePhone())) {
                response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
                response.setErrMsg("原手机号错误");
                return response;
            }
            user.setMobilePhone(request.getNewMobilePhone());
            user.setUpdateTime(new Date());
            userMapper.updateByExample(user, example);
        } catch (Exception e) {
            if (e.getClass() == DuplicateKeyException.class) {
                response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
                response.setErrMsg("该手机号已绑定另一用户");
            } else {
                response.setErrCode(Integer.toString(Constants.ErrCode.OPERATE_FAIL));
                response.setErrMsg(e.getMessage());
            }
        }
        return  response;
    }

    @Override
    public XSSFWorkbook exportUserList() {
        String sheetName = "用户表";
        String[] title = {"用户名", "昵称", "手机"};
        UserExample example = new UserExample();
        List<User> userList = userMapper.selectByExample(example);
        String[][] content = new String[userList.size()][3];
        if (CollectionUtils.isNotEmpty(userList)) {
            for (int i = 0; i < userList.size(); i++) {
                String[] row = content[i];
                User user = userList.get(i);
                row[0] = user.getUserAccount();
//                row[1] = new String(user.getNick().getBytes("ISO-8859-1"), "UTF-8");
                row[1] = user.getNick();
                row[2] = user.getMobilePhone();
            }
        }
        XSSFWorkbook workbook = ExcelUtils.getWorkBook(sheetName, title, content);
        return workbook;
    }

    @Override
    public CUser getUserInfoByAccount(String userAccount) {
        User user = userMapper.selectByAccount(userAccount);
        CUser cUser = new CUser();
        BeanUtils.copyProperties(cUser, user);
        return cUser;
    }
}
