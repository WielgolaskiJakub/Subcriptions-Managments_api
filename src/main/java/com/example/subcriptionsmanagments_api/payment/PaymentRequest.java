package com.example.subcriptionsmanagments_api.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class PaymentRequest {

    @NotNull(message = "Pole userId nie może być puste")
    private Long userId;

    @NotNull(message = "Pole subscriptionId nie może być puste")
    private Long subscriptionId;

    @NotNull(message = "Pole kwota nie może być puste")
    @Positive(message = "Kwota musi być dodatnia")
    private Double amount;

    @NotNull(message = "Pole data płatności nie może być puste")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @NotNull(message = "Status płatności nie może być pusty")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Metoda płatności nie może być pusta")
    private PaymentMethod paymentMethod;
}
