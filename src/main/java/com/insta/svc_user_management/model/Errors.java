package com.insta.svc_user_management.model;

import com.insta.svc_user_management.base.IBaseModel;
import com.insta.svc_user_management.enums.ErrorEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;

@Data
@Entity(name = "errors")
@Builder
public class Errors implements IBaseModel {
    @Serial
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "error")
    @Enumerated(EnumType.STRING)
    private ErrorEnum errorEnum;

    @Column(name = "code")
    private String code;

    @Column(name = "http_status")
    private String httpStatus;

    @Column(name = "description")
    private String description;
}
