package com.example.subcriptionsmanagments_api.subscription;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;

    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubscription(@PathVariable Long id) {
        try {
            Subscription subscription = subscriptionService.findById(id);
            return ResponseEntity.ok(subscription);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createSubscription(@RequestBody Subscription subscription) {
        try {
            Subscription savedSubscription = subscriptionService.addSubscription(subscription);
            return ResponseEntity.status(201).body(savedSubscription);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Blad podczas zapisywania subskrypcji: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription) {
        try {
            Subscription updatedSubscription = subscriptionService.updateByPutSubscription(id, subscription);
            return ResponseEntity.ok(updatedSubscription);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchSubscription(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Subscription patchedSubscription = subscriptionService.patchSubscription(id,updates);
        return ResponseEntity.ok(patchedSubscription);
    }

}
