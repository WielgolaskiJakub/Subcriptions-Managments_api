package com.example.subcriptionsmanagments_api.repository;

import com.example.subcriptionsmanagments_api.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
