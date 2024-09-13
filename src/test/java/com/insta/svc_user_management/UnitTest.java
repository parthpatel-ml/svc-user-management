package com.insta.svc_user_management;

import com.insta.svc_user_management.enums.GenderEnum;
import com.insta.svc_user_management.enums.UserAccountTypeEnum;
import com.insta.svc_user_management.enums.UserRoleEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class UnitTest {
    @Test
    void testGenderEnum(){
        System.out.println(Arrays.toString(GenderEnum.values()));
        System.out.println((GenderEnum.values().toString()));
    }

    @Test
    void testUserRoleEnum(){
        System.out.println((UserRoleEnum.ADMIN_ROLE.hashCode()));
        System.out.println((UserRoleEnum.ADMIN_ROLE.ordinal()));
        System.out.println((UserRoleEnum.USER_ROLE.ordinal()));
        System.out.println((UserRoleEnum.USER_ROLE.name()));

        System.out.println(UserAccountTypeEnum.NGO.name());
    }
}
