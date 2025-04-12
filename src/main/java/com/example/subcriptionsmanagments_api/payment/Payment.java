package com.example.subcriptionsmanagments_api.payment;


import com.example.subcriptionsmanagments_api.subscription.Subscription;
import com.example.subcriptionsmanagments_api.user.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table (name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "subsctription_id", nullable = false)
    private Subscription subscription;

    @Positive
    @NotNull(message = "Pole kwota, nie moze byc puste")
    private double amount;

    @NotNull(message = "Pole data platnosci nie moze byc puste")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status platnosci nie moze byc pusty")
    @Column(length = 30)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Metoda platnosci nie może byc pusta")
    @Column(length = 30)
    private PaymentMethod method;
}


//TODO: Dodać mock serwisu płatności i aktualizować status płatności na podstawie odpowiedzi
