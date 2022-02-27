package com.rento.property.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.rento.property.models.Property;
import com.rento.property.models.PropertyAddRequest;
import com.rento.property.services.PropertyService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(value = "/property/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add property")
    public ResponseEntity<Property> addProperty(@RequestBody PropertyAddRequest propertyAddRequest) {
        Property property = propertyService.addProperty(propertyAddRequest);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }
}
