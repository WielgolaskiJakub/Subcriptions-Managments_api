package com.example.subcriptionsmanagments_api.subscription;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "subscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
