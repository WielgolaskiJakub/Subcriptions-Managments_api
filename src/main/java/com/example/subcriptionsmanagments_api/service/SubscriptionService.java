package com.example.subcriptionsmanagments_api.service;

import com.example.subcriptionsmanagments_api.model.Subscription;
import com.example.subcriptionsmanagments_api.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subskrypcja o ID " + id + " nie istnieje!"));
    }

    public Subscription addSubscription(Subscription subscription) {
        if (subscription.getName() == null || subscription.getName().isEmpty()) {
            throw new IllegalArgumentException("Nazwa subskrypcji nie moze byc pusta!");
        }
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateSubscription(Long id, Subscription newSubscription) {
        return subscriptionRepository.findById(id).
                map(subscription -> {
                    subscription.setName(newSubscription.getName());
                    subscription.setPrice(newSubscription.getPrice());
                    subscription.setRenewalDate(newSubscription.getRenewalDate());
                    return subscriptionRepository.save(subscription);
                }).orElseThrow(() -> new RuntimeException("Subskrypcja o ID " + id + " nie istnieje"));
    }

}
