package com.insta.svc_user_management.model;

import com.insta.svc_user_management.base.IBaseModel;
import com.insta.svc_user_management.enums.UserAccountTypeEnum;
import com.insta.svc_user_management.enumsconverter.UserAccountTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@Entity(name = "user_account_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountType implements IBaseModel {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_account_type")
    @Convert(converter = UserAccountTypeConverter.class)
    private UserAccountTypeEnum userAccountTypeEnum;

    @Column(name = "code")
    private String code;
}
