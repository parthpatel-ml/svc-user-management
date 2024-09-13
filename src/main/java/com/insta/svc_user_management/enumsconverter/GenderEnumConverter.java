package com.insta.svc_user_management.enumsconverter;

import com.insta.svc_user_management.enums.GenderEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class GenderEnumConverter implements AttributeConverter<GenderEnum, String> {
    @Override
    public String convertToDatabaseColumn(GenderEnum attribute) {
        return switch (attribute) {
            case MALE -> "MALE";
            case FEMALE -> "FEMALE";
            case TRANSGENDER -> "TRANSGENDER";
            case OTHER -> "OTHER";
        };
    }

    @Override
    public GenderEnum convertToEntityAttribute(String dbData) {
        return switch (dbData) {
            case "MALE" -> GenderEnum.MALE;
            case "FEMALE" -> GenderEnum.FEMALE;
            case "TRANSGENDER" -> GenderEnum.TRANSGENDER;
            case "OTHER" -> GenderEnum.OTHER;
            default -> throw new IllegalStateException("Unexpected db value: " + dbData);
        };
    }
}
