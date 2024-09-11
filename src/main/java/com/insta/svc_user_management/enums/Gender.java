package com.insta.svc_user_management.enums;

import com.insta.svc_user_management.base.BaseEnum;

public enum Gender implements BaseEnum {
    MALE(1, "MALE"),
    FEMALE(2, "FEMALE");

    private int id;
    private String name;

    Gender(int id, String name) {
        this.id = id;
        this.name = name;

    }
}
