package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.base.IBaseEnum;

public enum Gender implements IBaseEnum {
    MALE(1, "MALE"),
    FEMALE(2, "FEMALE");

    private int id;
    private String name;

    Gender(int id, String name) {
        this.id = id;
        this.name = name;

    }
}
