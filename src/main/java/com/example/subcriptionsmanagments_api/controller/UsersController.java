package com.example.subcriptionsmanagments_api.controller;


import com.example.subcriptionsmanagments_api.model.Users;
import com.example.subcriptionsmanagments_api.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        try {
            Users users = usersService.getUserById(id);
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody Users user) {
        try{
            Users users = usersService.createUser(user);
            return ResponseEntity.status(201).body(users);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.status(500).body("Blad pdczas zapisywania uzytkownika " + e.getMessage());
        }

    }
}

//walidacja danych, zautomatyzowana, globalna obsluga błędów