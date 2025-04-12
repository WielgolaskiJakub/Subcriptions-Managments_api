package com.example.subcriptionsmanagments_api.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UsersRequest {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa uzytkownika nie moze byc pusta")
    private String username;

    @Email
    @NotBlank(message = "Pole email nie moze byc puste")
    private String email;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]+$",
            message = "Haslo musi zawierac co najmniej 1 duza litere oraz 1 cyfre"
    )
    @NotBlank(message = "Pole haslo nie moze byc puste")
    @Size(min = 6, max = 32, message = "Haslo musi miec od 6 do 32 znakow")
    private String password;

}
