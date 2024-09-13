package com.insta.svc_user_management.model;

import com.insta.svc_user_management.base.IBaseModel;
import com.insta.svc_user_management.enums.UserRoleEnum;
import com.insta.svc_user_management.enumsconverter.UserRoleConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;

@Data
@Entity(name = "user_role")
public class UserRole implements IBaseModel {
    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_role")
    @Convert(converter = UserRoleConverter.class)
    private UserRoleEnum userRoleEnum;
}
