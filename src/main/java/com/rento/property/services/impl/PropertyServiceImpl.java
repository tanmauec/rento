package com.rento.property.services.impl;

import com.rento.core.constants.Constants;
import com.rento.core.utils.IdGenerator;
import com.rento.property.models.Property;
import com.rento.property.models.PropertyAddRequest;
import com.rento.property.models.PropertyRentRequest;
import com.rento.property.repository.PropertyRepository;
import com.rento.property.services.PropertyService;
import com.rento.property.storage.StoredProperty;
import com.rento.property.utils.ConverterUtil;
import com.rento.users.models.Owner;
import com.rento.users.models.User;
import com.rento.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rento.core.constants.Constants.PROP_ID_PREFIX;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserService userService;

    public Property addProperty(PropertyAddRequest addRequest) {
        StoredProperty storedProperty = ConverterUtil.toDao(addRequest);
        storedProperty.setPropertyId(IdGenerator.generateNumericId(PROP_ID_PREFIX));
        return ConverterUtil.toDto(propertyRepository.save(storedProperty));
    }

    public Property rentProperty(PropertyRentRequest rentRequest) {
//        verifyTenants(rentRequest.getTenants());
        return null;
    }

    public Owner getOwner(String propertyId) {
        return null;
    }

    public List<User> getTenant(String propertyId) {
        return null;
    }
}
