package com.rento.property.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Property {
    private String propertyId;
    private PropertyType type;
    private String ownerId;
    private String constructionYear;
}
