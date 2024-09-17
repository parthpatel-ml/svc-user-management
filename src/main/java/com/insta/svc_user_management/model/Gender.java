package com.insta.svc_user_management.model;

import com.insta.svc_user_management.base.IBaseModel;
import com.insta.svc_user_management.enums.GenderEnum;
import com.insta.svc_user_management.enumsconverter.GenderEnumConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;

@Data
@Entity(name = "gender")
@Builder
public class Gender implements IBaseModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = GenderEnumConverter.class)
    @Column(name = "gender")
    private GenderEnum genderEnum;

    @Column(name = "code")
    private String code;
}
