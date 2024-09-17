package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.base.IBaseEnum;

public enum GenderEnum implements IBaseEnum {
    MALE(1,"M"),
    FEMALE(2, "F"),
    TRANSGENDER(3,"T"),
    OTHER(4,"O");

    public int id;
    public String code;

    GenderEnum(int id, String code) {
        this.id = id;
        this.code = code;
    }
}
