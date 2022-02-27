package com.rento.verification.controller;


import com.rento.verification.models.*;
import com.rento.verification.services.impl.TenantVerificationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class VerificationController {

    @Autowired
    private TenantVerificationService tenantVerificationService;

    @RequestMapping(value = "/consent/init", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Tenant Verify Init", notes = "")
    public ResponseEntity<TenantConsentInitResponse> initTenantVerification(@RequestBody TenantConsentInitRequest request) {
        TenantConsentInitResponse response = tenantVerificationService.consentInit(request);
        //response.getConsentResponse().setDetail(null);
        return new ResponseEntity<TenantConsentInitResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/consent/complete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch Tenant Data", notes = "")
    public ResponseEntity<FetchTenantDataInitResponse> fetchTenantData(@RequestBody FetchTenantDataInitRequest request) {
        return new ResponseEntity<FetchTenantDataInitResponse>(tenantVerificationService.fetchData(request), HttpStatus.OK);
    }

    @RequestMapping(value = "/rentalscore/{tenantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Rental score of Potential Tenant")
    public ResponseEntity<RentalScore> computeRentalScore(@PathVariable("tenantId") String phoneNumber) {
        RentalScore rentalScore = RentalScore.builder()
                .score(0.8)
                .rentDefaultProbability(0.2)
                .build();
        return new ResponseEntity<RentalScore>(rentalScore, HttpStatus.OK);
    }

}
