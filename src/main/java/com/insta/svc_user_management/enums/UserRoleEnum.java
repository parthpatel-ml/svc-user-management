package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.base.IBaseEnum;

public enum UserRoleEnum implements IBaseEnum {
    ADMIN_ROLE(1,"AR"),
    USER_ROLE(2,"UR"),
    MANAGER_ROLE(3,"MR"),
    SALES_ROLE(4,"SR"),
    OPERATIONAL_ROLE(5,"OR"),
    REPORT_GENERATOR_ROLE(6,"RGR");

    int id;
    String code;

    UserRoleEnum(int id, String code) {
        this.id = id;
        this.code = code;
    }
}
