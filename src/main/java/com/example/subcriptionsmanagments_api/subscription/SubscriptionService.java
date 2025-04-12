package com.example.subcriptionsmanagments_api.subscription;

import com.example.subcriptionsmanagments_api.globalExceptionHandler.ApiException;
import com.example.subcriptionsmanagments_api.globalExceptionHandler.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<SubscriptionResponse> getAllSubscriptions() {

        return subscriptionRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public SubscriptionResponse getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.SUBSCRIPTION_NOT_FOUND, id));
        return mapToResponse(subscription);
    }

    public SubscriptionResponse addSubscription(SubscriptionRequest request) {
        Subscription subscription = new Subscription();
        Subscription savedSubscription = getSubscription(request, subscription);
        return mapToResponse(savedSubscription);
    }

    public SubscriptionResponse updateByPutSubscription(Long id, SubscriptionRequest request) {
       Subscription subscription = subscriptionRepository.findById(id)
        .orElseThrow(() -> new ApiException(ErrorCode.SUBSCRIPTION_NOT_FOUND, id));
       Subscription savedSubscription = getSubscription(request, subscription);
       return mapToResponse(savedSubscription);
    }

    public SubscriptionResponse patchSubscription(Long id, SubscriptionPatchRequest request) {
        Subscription subscription = subscriptionRepository.findById(id).
                orElseThrow(() -> new ApiException(ErrorCode.SUBSCRIPTION_NOT_FOUND, id));

        if(request.getName() != null) {
            subscription.setName(request.getName());
        }
        if(request.getRenewalDate() != null) {
            subscription.setRenewalDate(request.getRenewalDate());
        }
        if(request.getPrice() != null) {
            subscription.setPrice(request.getPrice());
        }
    Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapToResponse(savedSubscription);
    }


    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new ApiException(ErrorCode.SUBSCRIPTION_NOT_FOUND, id);
        }
        subscriptionRepository.deleteById(id);
    }

    private Subscription getSubscription(SubscriptionRequest request , Subscription subscription){
        subscription.setName(request.getName());
        subscription.setPrice(request.getPrice());
        subscription.setRenewalDate(request.getRenewalDate());
        return subscriptionRepository.save(subscription);


    }

    public SubscriptionResponse mapToResponse(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getName(),
                subscription.getPrice(),
                subscription.getRenewalDate()
        );
    }


}
