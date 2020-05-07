package com.Pipi.demo.dto.request;

import com.Pipi.demo.entity.custom.CUser;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = -6144139330173730021L;

    CUser user;
}
