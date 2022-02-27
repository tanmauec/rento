package com.rento.property.models;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Property {
    private String propertyId;
    private PropertyType type;
    private String ownerId;
    private String constructionYear;
}
