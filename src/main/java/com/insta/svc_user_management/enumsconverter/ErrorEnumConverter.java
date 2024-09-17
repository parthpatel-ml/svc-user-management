package com.insta.svc_user_management.enumsconverter;

import com.insta.svc_user_management.enums.ErrorEnum;
import com.insta.svc_user_management.enums.GenderEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ErrorEnumConverter implements AttributeConverter<ErrorEnum, String> {
    @Override
    public String convertToDatabaseColumn(ErrorEnum attribute) {
        return switch (attribute) {
            case E001 -> "E001";
            case E002 -> "E002";
            case E003 -> "E003";
        };
    }

    @Override
    public ErrorEnum convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "E001" -> ErrorEnum.E001;
            case "E002" -> ErrorEnum.E002;
            case "E003" -> ErrorEnum.E003;
            default -> throw new IllegalStateException("Unexpected db value: " + dbData);
        };
    }
}
