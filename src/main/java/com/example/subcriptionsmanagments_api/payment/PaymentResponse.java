package com.example.subcriptionsmanagments_api.payment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PaymentResponse {

    private long id;
    private Long userId;
    private long subscriptionId;
    private double amount;
    private LocalDate paymentDate;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;


    public PaymentResponse(long id, long userId, long subscriptionId,
                          double amount, LocalDate paymentDate,
                          PaymentStatus paymentStatus,  PaymentMethod method){
        this.id = id;
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = method;
    }

}
