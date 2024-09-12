package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.base.IBaseEnum;

public enum GenderEnum implements IBaseEnum {
    MALE(1, "MALE","M"),
    FEMALE(2, "FEMALE", "F"),
    TRANSGENDER(3,"TRANSGENDER","T");


    private int id;
    private String name;
    private String code;

    GenderEnum(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
}
