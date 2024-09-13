package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.base.IBaseEnum;

public enum UserAccountTypeEnum implements IBaseEnum {
    PRIVATE(1, "PRI"),
    PUBLIC(2, "PUB"),
    NGO(3, "NGO"),
    GOVERNMENT(4, "GOV"),
    GROUP(5, "GRP");

    int id;
    String code;

    UserAccountTypeEnum(int id, String code) {
        this.id = id;
        this.code = code;
    }

}
