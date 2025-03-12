package org.example.subcriptionsmanagments_api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController {

    @GetMapping("/test")
    public int test(){
        return 1;
    }
}
