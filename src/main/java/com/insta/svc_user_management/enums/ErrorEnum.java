package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.base.IBaseEnum;
import org.springframework.http.HttpStatus;

public enum ErrorEnum implements IBaseEnum {
    E001("E50001", HttpStatus.CREATED, ""),
    E002("E50002", HttpStatus.CONTINUE, ""),
    E003( "E50003", HttpStatus.MULTI_STATUS, "");

    public String code;
    public HttpStatus httpStatus;
    public String description;

    ErrorEnum(String code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }

}
