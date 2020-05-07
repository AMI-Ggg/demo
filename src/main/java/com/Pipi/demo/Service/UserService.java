package com.Pipi.demo.Service;

import com.Pipi.demo.Utils.DateTool;
import com.Pipi.demo.dto.request.MobilePhoneRequest;
import com.Pipi.demo.dto.request.NickRequest;
import com.Pipi.demo.dto.request.PasswordRequest;
import com.Pipi.demo.dto.request.UserAddRequest;
import com.Pipi.demo.dto.response.BaseResponse;
import com.Pipi.demo.entity.custom.CUser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface UserService {
    /**
     * 新增用户
     */
    BaseResponse addUser(UserAddRequest request);

    /**
     * 修改密码
     */
    BaseResponse alterPassword(PasswordRequest request);

    /**
     * 修改昵称
     */
    BaseResponse alterNick(NickRequest request);

    /**
     * 修改绑定手机
     */
    BaseResponse alterMobilePhone(MobilePhoneRequest request);

    /**
     * 导出用户列表
     */
    XSSFWorkbook exportUserList();

    /**
     * 通过用户名获取用户信息
     */
    CUser getUserInfoByAccount(String userAccount);
}
