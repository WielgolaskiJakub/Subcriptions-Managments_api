package com.example.subcriptionsmanagments_api.subscription;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionResponse {

    private long id;
    private String name;
    private double price;
    private LocalDate renewalDate;


    public SubscriptionResponse(long id, String name, double price, LocalDate renewalDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.renewalDate = renewalDate;
    }
}
