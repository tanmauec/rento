package com.rento.rent.services.impl;

import com.rento.core.async.AsyncUtils;
import com.rento.core.utils.IdGenerator;
import com.rento.core.utils.SerDe;
import com.rento.rent.models.*;
import com.rento.rent.repository.RentPaymentRepository;
import com.rento.rent.repository.RentRepository;
import com.rento.rent.services.RentService;
import com.rento.rent.storage.StoredLease;
import com.rento.rent.storage.StoredRentHandshakes;
import com.rento.rent.storage.StoredRentPayments;
import com.rento.rent.utils.ConverterUtil;
import com.rento.users.models.State;
import com.rento.rent.repository.RentHandshakeRepository;
import com.rento.users.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.rento.core.constants.Constants.LEASE_ID_PREFIX;

@Slf4j
@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private RentHandshakeRepository rentHandshakeRepository;

    @Autowired
    private RentPaymentRepository rentPaymentRepository;

    @Autowired
    private UserService userService;


    /**
     * The Rest template.
     */
    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RentService.class);
    @Override
    public LeaseDetails createLeaseDetails(CreateLeaseRequest request) {
        StoredLease storedLease = ConverterUtil.toDao(request);
        String leaseId = IdGenerator.generateNumericId(LEASE_ID_PREFIX);
        storedLease.setLeaseId(leaseId);
        storedLease.setState(State.NOT_ACTIVATED);
        LeaseDetails leaseDetails = ConverterUtil.toDto(rentRepository.save(storedLease));
        leaseDetails.getTenants().forEach(tenantId -> AsyncUtils.submit(() -> notifyRentTerms(leaseDetails, tenantId)));
        return ConverterUtil.toDto(rentRepository.save(storedLease));
    }

    @Override
    public boolean acceptRentTerms(String leaseId, String tenantId) {
        StoredLease storedLease = rentRepository.getLeaseByLeaseId(leaseId);
        StoredRentHandshakes rentAcceptance = rentHandshakeRepository.getHandshakeByLeaseIdTenantId(tenantId, leaseId);
        if (Objects.nonNull(rentAcceptance)) {
            rentAcceptance.setStatus(LeaseStatus.ACCEPTED);
            rentAcceptance = rentHandshakeRepository.save(rentAcceptance);
        } else {
            rentAcceptance = rentHandshakeRepository.save(StoredRentHandshakes.builder()
                    .leaseId(leaseId)
                    .tenantId(tenantId)
                    .status(LeaseStatus.ACCEPTED)
                    .build());
        }

        postRentTermsAcceptance(storedLease);

        return rentAcceptance.getStatus().equals(LeaseStatus.ACCEPTED);
    }

    @Override
    public boolean payRent(RentPaymentRequest rentPaymentRequest) {
        StoredRentPayments payment = rentPaymentRepository.save(StoredRentPayments.builder()
                .transactionId(IdGenerator.generateNumericId("PAY"))
                .propertyId(rentPaymentRequest.getPropertyId())
                .tenantId(rentPaymentRequest.getTenantId())
                .rentMonth(rentPaymentRequest.getRentMonth())
                .amount(rentPaymentRequest.getAmount())
                .build());
        return Objects.nonNull(payment);

    }

    @Override
    public List<RentPayment> getAllPayments(String tenantId) {
        return rentPaymentRepository.getPaymentsByTenantId(tenantId)
                .stream().map(ConverterUtil::toDto)
                .collect(Collectors.toList());
    }

    private void postRentTermsAcceptance(StoredLease storedLease) {
        if (! allTenantsHaveAcceptedRentTerms(storedLease))
            return;

        storedLease.setState(State.ENABLED);
        rentRepository.save(storedLease);
        notifyAllPartiesRentTermAccepted();
    }

    private void notifyAllPartiesRentTermAccepted() {
    }

    private boolean allTenantsHaveAcceptedRentTerms(StoredLease storedLease) {
         return storedLease.getTenantCount() == rentHandshakeRepository.getHandshakesByLeaseId(storedLease.getLeaseId())
                .stream().filter(handshake -> handshake.getStatus().equals(LeaseStatus.ACCEPTED))
                .count();
    }


    private void notifyRentTerms(LeaseDetails leaseDetails, String tenantId) {
        String deviceToken = null;
        String message = "hi tenant " + SerDe.writeValueAsString(leaseDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization",
                "key=AAAAw47Vieg:APA91bHfdBLEcUUrxo_53WZBVWZZT5xFQyQLQjmThyi8gUBY20bxECsc1xirK3I9N5L_a9FTpy7a0f81ngpNlBKo3nys-FgkUALA7VzoNCceDI9PaRhvxqtG9s0QveuGb_c7tOKOXT89");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "rentagreement");
        jsonObject.put("body", leaseDetails);
        try {
            deviceToken = userService.getUserById(tenantId).get().getDeviceId();
        } catch (Exception ex) {
            logger.error("Failed to get device token of tenant {}, reason {}", tenantId, ex.getLocalizedMessage());
        }

        if (Objects.isNull(deviceToken)) {
            logger.info(String.format("Tenant %s deviceId not exist, cant send rent agreement", tenantId));
        }

        jsonObject.put("to", deviceToken);

        String url = "https://fcm.googleapis.com/fcm/send"; //todo extract config property
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, request, Object.class);
        logger.info("Rent terms sent to tenant {}, with details: {} response: {}", tenantId, leaseDetails, responseEntity.toString());
    }
}
