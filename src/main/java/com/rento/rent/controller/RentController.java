package com.rento.rent.controller;

import com.google.common.base.Preconditions;
import com.rento.rent.models.CreateLeaseRequest;
import com.rento.rent.models.LeaseDetails;
import com.rento.rent.models.RentPayment;
import com.rento.rent.models.RentPaymentRequest;
import com.rento.rent.services.RentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1")
@Slf4j
@Validated
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping(value = "lease/creation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rent Initiation", notes = "")
    public ResponseEntity<LeaseDetails> createLease(@RequestBody CreateLeaseRequest request) {
        Preconditions.checkArgument(
                 ! CollectionUtils.isEmpty(request.getTenantIds())
        );

        LeaseDetails leaseDetails = rentService.createLeaseDetails(request);
        return new ResponseEntity<LeaseDetails>(leaseDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "lease/{leaseId}/acceptedby/{tenantId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rent terms acceptance")
    public ResponseEntity<Boolean> acceptRentTerms(@PathVariable("leaseId") String leaseId,
                                                   @PathVariable("tenantId") String tenantId) {
        boolean success = rentService.acceptRentTerms(leaseId, tenantId);
        return new ResponseEntity<Boolean>(success, HttpStatus.OK);
    }

    @RequestMapping(value = "rent/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Pay rent")
    public ResponseEntity<Boolean> payRent(RentPaymentRequest rentPaymentRequest) {
        return new ResponseEntity<Boolean>(rentService.payRent(rentPaymentRequest), HttpStatus.OK);
    }

    @RequestMapping(value = "rent/payments/{tenantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get All rental payments made by Tenant")
    public ResponseEntity<List<RentPayment>> getAllPayments(@PathVariable("tenantId") String tenantId) {
        return new ResponseEntity<List<RentPayment>>(rentService.getAllPayments(tenantId), HttpStatus.OK);
    }

}
