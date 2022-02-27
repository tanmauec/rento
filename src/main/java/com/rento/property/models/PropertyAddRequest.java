package com.rento.property.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyAddRequest {
    @NotBlank
    private String ownerId;
    @NotBlank
    private String name;

    @NotNull
    private PropertyType type;

//    private Address address;
}
