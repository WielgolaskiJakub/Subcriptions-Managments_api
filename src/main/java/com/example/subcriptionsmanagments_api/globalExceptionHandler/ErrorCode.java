package com.example.subcriptionsmanagments_api.globalExceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "error.user.not_found"),
    SUBSCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "error.subscription.not_found"),
    PAYMENT_INVALID(HttpStatus.BAD_REQUEST, "payment.invalid"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "error.internal"),
    INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "error.invalid.argument"),
    USERNAME_REQUIRED(HttpStatus.BAD_REQUEST, "error.username.required"),
    SUBSCRIPTION_NAME_REQUIRED(HttpStatus.BAD_REQUEST, "error.subscription.name.required"),
    INCORRECT_DATA_FORMAT(HttpStatus.BAD_REQUEST, "error.incorrect.data_format"),
    SUBSCRIPTION_PATCH_UNKNOWN_FIELD(HttpStatus.BAD_REQUEST, "error.subscription.patch.unknown_field"),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "error.payment.not_found");
    private final HttpStatus status;
    private final String messageKey;

    ErrorCode(HttpStatus status, String messageKey) {
        this.status = status;
        this.messageKey = messageKey;
    }
}
