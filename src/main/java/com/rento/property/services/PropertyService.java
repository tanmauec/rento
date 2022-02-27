package com.rento.property.services;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.rento.property.models.Property;
import com.rento.property.models.PropertyAddRequest;
import com.rento.property.models.PropertyRentRequest;
import com.rento.users.models.Owner;
import com.rento.users.models.User;

import java.util.List;

public interface PropertyService {

    Property addProperty(PropertyAddRequest addRequest);

    List<Property> getProperties(String ownerId);


    Property rentProperty(PropertyRentRequest rentRequest);

    Owner getOwner(String propertyId);

    List<User> getTenant(String propertyId);
}
