package com.Pipi.demo.dto.response;

import lombok.Data;
import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -1539185639383230946L;

    private String errCode;

    private String errMsg;
}
