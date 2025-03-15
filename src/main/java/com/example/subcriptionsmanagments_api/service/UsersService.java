package com.example.subcriptionsmanagments_api.service;

import com.example.subcriptionsmanagments_api.model.Users;
import com.example.subcriptionsmanagments_api.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Uzytkownik o ID " + id + " nie istnieje!"));
    }

    public Users createUser(Users user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Uzytkownik musi posiadac nazwe");
        }
        return usersRepository.save(user);
    }
}
