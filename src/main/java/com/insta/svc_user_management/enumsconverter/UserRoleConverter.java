package com.insta.svc_user_management.enumsconverter;

import com.insta.svc_user_management.enums.UserRoleEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<UserRoleEnum, String> {
    @Override
    public String convertToDatabaseColumn(UserRoleEnum attribute) {
        return switch (attribute) {
            case ADMIN_ROLE -> "ADMIN_ROLE";
            case USER_ROLE -> "USER_ROLE";
            case MANAGER_ROLE -> "MANAGER_ROLE";
            case OPERATIONAL_ROLE -> "OPERATIONAL_ROLE";
            case SALES_ROLE -> "SALES_ROLE";
            case REPORT_GENERATOR_ROLE -> "REPORT_GENERATOR_ROLE";
        };
    }

    @Override
    public UserRoleEnum convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "ADMIN_ROLE" -> UserRoleEnum.ADMIN_ROLE;
            case "USER_ROLE" -> UserRoleEnum.USER_ROLE;
            case "MANAGER_ROLE" -> UserRoleEnum.MANAGER_ROLE;
            case "OPERATIONAL_ROLE" -> UserRoleEnum.OPERATIONAL_ROLE;
            case "SALES_ROLE" -> UserRoleEnum.SALES_ROLE;
            case "REPORT_GENERATOR_ROLE" -> UserRoleEnum.REPORT_GENERATOR_ROLE;
            default -> throw new IllegalStateException("Unexpected db value: " + dbData);

        };
    }
}
