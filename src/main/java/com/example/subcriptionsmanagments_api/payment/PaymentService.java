package com.example.subcriptionsmanagments_api.payment;


import com.example.subcriptionsmanagments_api.globalExceptionHandler.ApiException;
import com.example.subcriptionsmanagments_api.globalExceptionHandler.ErrorCode;
import com.example.subcriptionsmanagments_api.subscription.Subscription;
import com.example.subcriptionsmanagments_api.subscription.SubscriptionRepository;
import com.example.subcriptionsmanagments_api.user.Users;
import com.example.subcriptionsmanagments_api.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UsersRepository usersRepository;
    private final SubscriptionRepository subscriptionRepository;
    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UsersRepository usersRepository,
                          SubscriptionRepository subscriptionRepository) {
        this.paymentRepository = paymentRepository;
        this.usersRepository = usersRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<PaymentResponse> getAllPayments(){
        return paymentRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public PaymentResponse getPaymentById(long id){
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.PAYMENT_NOT_FOUND, id));
        return mapToResponse(payment);
    }

    public PaymentResponse createPayment(PaymentRequest request){
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND, request.getUserId()));

        Subscription subscription = subscriptionRepository.findById(request.getSubscriptionId())
                .orElseThrow(() -> new ApiException(ErrorCode.SUBSCRIPTION_NOT_FOUND, request.getSubscriptionId()));

        Payment payment = new Payment();
        Payment savedPayment = getPayment(request, user, subscription, payment);
        return mapToResponse(savedPayment);
    }

    public PaymentResponse updatePayment(long id, PaymentRequest request){
        Users user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_FOUND, request.getUserId()));

        Subscription subscription = subscriptionRepository.findById(request.getSubscriptionId())
                .orElseThrow(()-> new ApiException(ErrorCode.SUBSCRIPTION_NOT_FOUND, request.getSubscriptionId()));

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.PAYMENT_NOT_FOUND, id));
        Payment updatedPayment = getPayment(request, user, subscription, payment);
        return mapToResponse(updatedPayment);

    }

    public void deletePayment(long id){
        if(!paymentRepository.existsById(id)){
            throw new ApiException(ErrorCode.PAYMENT_NOT_FOUND, id);
        }
        paymentRepository.deleteById(id);
    }

    private Payment getPayment(PaymentRequest request, Users user, Subscription subscription, Payment payment) {
        payment.setUser(user);
        payment.setSubscription(subscription);
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setPaymentStatus(request.getPaymentStatus());
        payment.setMethod(request.getPaymentMethod());
        return paymentRepository.save(payment);
    }

    public PaymentResponse mapToResponse(Payment payment){
        return new PaymentResponse(
                payment.getId(),
                payment.getUser().getId(),
                payment.getSubscription().getId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentStatus(),
                payment.getMethod()
        );
    }
}
