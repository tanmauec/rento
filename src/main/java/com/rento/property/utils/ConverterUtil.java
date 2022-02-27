package com.rento.property.utils;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.rento.property.models.Property;
import com.rento.property.models.PropertyAddRequest;
import com.rento.property.storage.StoredProperty;

public class ConverterUtil {

    public static StoredProperty toDao(PropertyAddRequest request) {
        return StoredProperty.builder()
                .ownerId(request.getOwnerId())
                .name(request.getName())
                .type(request.getType())
                .build();
    }

    public static Property toDto(StoredProperty storedProperty) {
        return Property.builder()
                .propertyId(storedProperty.getPropertyId())
                .ownerId(storedProperty.getOwnerId())
                .type(storedProperty.getType())
                .build();
    }
}
