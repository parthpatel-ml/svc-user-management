package com.insta.svc_user_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.insta.svc_user_management.base.IBaseDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.sql.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailDTO implements IBaseDTO {
    @Serial
    @JsonIgnore
    private static final long serialVersionUID = 1001L;

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    @JsonProperty("number")
    private String number;
    private Date birthDate;
    private String password;
    private String bio;
    private String[] links;
    private String gender;
    @NotEmpty
    @NotNull
    private String userRole;
    private String userAccountType;

}
