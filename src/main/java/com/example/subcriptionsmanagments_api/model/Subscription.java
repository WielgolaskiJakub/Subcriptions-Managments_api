package com.example.subcriptionsmanagments_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Pole cena subskrypcji nie moze byc puste")
    private double price;

    @NotBlank(message = "Pole data odnowienia nie moze byc puste")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate renewalDate;
}
