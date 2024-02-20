package com.trade_platform.Entity.Trade.Reference;

import com.trade_platform.Entity.Category;
import com.trade_platform.Service.Converter.CategoryTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractReferenceEntity {
    @Column(name = "name", length = 63, nullable = false)
    protected String name;

    @Column(name = "type", length = 63, nullable = false)
    @Convert(converter = CategoryTypeConverter.class)
    protected Category.Type type;

    @Column(name = "is_approved", nullable = false)
    protected boolean isApproved = false;

    @Column(name = "is_show_in_catalog", nullable = false)
    protected boolean isShowInCatalog = true;
}