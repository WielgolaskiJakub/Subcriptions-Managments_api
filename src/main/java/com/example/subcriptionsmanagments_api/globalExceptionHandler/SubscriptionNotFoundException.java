package com.example.subcriptionsmanagments_api.globalExceptionHandler;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
