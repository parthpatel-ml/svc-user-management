package com.insta.svc_user_management.enumsconverter;

import com.insta.svc_user_management.enums.UserAccountTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserAccountTypeConverter implements AttributeConverter<UserAccountTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(UserAccountTypeEnum attribute) {
        return switch (attribute){
            case PRIVATE -> "PRIVATE";
            case PUBLIC -> "PUBLIC";
            case NGO -> "NGO";
            case GOVERNMENT -> "GOVERNMENT";
            case GROUP -> "GROUP";
        };
    }

    @Override
    public UserAccountTypeEnum convertToEntityAttribute(String dbData) {
        return switch (dbData){
            case "PRIVATE" -> UserAccountTypeEnum.PRIVATE;
            case "PUBLIC" -> UserAccountTypeEnum.PUBLIC;
            case "NGO" -> UserAccountTypeEnum.NGO;
            case "GOVERNMENT" -> UserAccountTypeEnum.GOVERNMENT;
            case "GROUP" -> UserAccountTypeEnum.GROUP;
            default -> throw new IllegalStateException("Unexpected db value: " + dbData);
        };
    }
}
