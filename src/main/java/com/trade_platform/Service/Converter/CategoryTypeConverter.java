package com.trade_platform.Service.Converter;

import com.trade_platform.Entity.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CategoryTypeConverter implements AttributeConverter<Category.Type, String> {
    @Override
    public String convertToDatabaseColumn(Category.Type type) {
        return null == type ? null : type.getSlug();
    }

    @Override
    public Category.Type convertToEntityAttribute(String value) {
        if (null == value) {
            return null;
        }

        return Stream.of(Category.Type.values())
            .filter(ct -> ct.getSlug().equals(value))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new)
        ;
    }
}