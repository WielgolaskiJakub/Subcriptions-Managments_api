package com.example.subcriptionsmanagments_api.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class SubscriptionRequest {
    private Long id;

    @NotBlank(message = "Nazwa subskrypcji musi zawierac nazwe")
    private String name;

    @Positive
    @NotNull(message = "Pole cena subskrypcji nie moze byc puste")
    private double price;

    @NotNull(message = "Pole data odnowienia nie moze byc puste")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate renewalDate;
}
