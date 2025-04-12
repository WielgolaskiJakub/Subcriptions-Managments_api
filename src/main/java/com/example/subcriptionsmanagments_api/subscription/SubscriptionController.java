package com.example.subcriptionsmanagments_api.subscription;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;

    }

    @GetMapping
    public List<SubscriptionResponse> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable Long id) {
            SubscriptionResponse response = subscriptionService.getSubscriptionById(id);
            return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody @Valid SubscriptionRequest request) {
            SubscriptionResponse savedSubscription = subscriptionService.addSubscription(request);
            return ResponseEntity.status(201).body(savedSubscription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> updateSubscription(@PathVariable Long id, @RequestBody @Valid SubscriptionRequest request) {
            SubscriptionResponse updatedSubscription = subscriptionService.updateByPutSubscription(id, request);
            return ResponseEntity.ok(updatedSubscription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> patchSubscription(
            @PathVariable Long id,
            @RequestBody @Valid SubscriptionPatchRequest request) {
        SubscriptionResponse updatedSubscription = subscriptionService.patchSubscription(id, request);

        return ResponseEntity.ok(updatedSubscription);
    }

}
