package com.insta.svc_user_management.model;

import com.insta.svc_user_management.base.IBaseModel;
import com.insta.svc_user_management.enums.UserAccountTypeEnum;
import com.insta.svc_user_management.enumsconverter.UserAccountTypeConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;

@Data
@Entity(name = "user_account_type")
public class UserAccountType implements IBaseModel {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_account_type")
    @Convert(converter = UserAccountTypeConverter.class)
    private UserAccountTypeEnum userAccountTypeEnum;

}
