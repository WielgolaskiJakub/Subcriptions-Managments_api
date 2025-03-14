package com.example.subcriptionsmanagments_api.controller;


import com.example.subcriptionsmanagments_api.model.Subscription;
import com.example.subcriptionsmanagments_api.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;

    }

    @GetMapping("")
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long id) {
        try {
            Subscription subscription = subscriptionService.findById(id);
            return ResponseEntity.ok(subscription);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createSubscription(@RequestBody Subscription subscription) {
       try{
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
       try{
           Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscription);
           return ResponseEntity.ok(updatedSubscription);
       } catch (RuntimeException e) {
           return ResponseEntity.status(400).body(e.getMessage());
       }
    }
}
