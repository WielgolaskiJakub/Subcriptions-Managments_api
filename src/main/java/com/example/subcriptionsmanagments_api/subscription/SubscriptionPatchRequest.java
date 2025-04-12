package com.example.subcriptionsmanagments_api.subscription;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class SubscriptionPatchRequest {

    private String name;
    private Double price;
    private LocalDate renewalDate;


    public SubscriptionPatchRequest(String name, double price, LocalDate renewalDate) {
        this.name = name;
        this.price = price;
        this.renewalDate = renewalDate;
    }
}
