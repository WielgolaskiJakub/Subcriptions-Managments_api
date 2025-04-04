package com.example.subcriptionsmanagments_api.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> findAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subskrypcja o ID " + id + " nie istnieje!"));
    }

    public Subscription addSubscription(Subscription subscription) {
        if (subscription.getName() == null || subscription.getName().isEmpty()) {
            throw new IllegalArgumentException("Nazwa subskrypcji nie moze byc pusta!");
        }
        return subscriptionRepository.save(subscription);
    }

    public Subscription updateByPutSubscription(Long id, Subscription newSubscription) {
        return subscriptionRepository.findById(id).map(subscription -> {
            subscription.setName(newSubscription.getName());
            subscription.setPrice(newSubscription.getPrice());
            subscription.setRenewalDate(newSubscription.getRenewalDate());
            return subscriptionRepository.save(subscription);
        }).orElseThrow(() -> new RuntimeException("Subskrypcja o ID " + id + " nie istnieje"));
    }

    public Subscription patchSubscription(Long id, Map<String, Object> updates) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Subskrypcja o " + id + " nie istnieje"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    subscription.setName((String) value);
                    break;
                case "price":
                    subscription.setPrice((Double.parseDouble(value.toString())));
                    break;
                case "renewalDate": // Obsługa daty
                    if (value instanceof String) {
                        try {
                            LocalDate parsedDate = LocalDate.parse((String) value);
                            subscription.setRenewalDate(parsedDate);
                        } catch (DateTimeParseException e) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowy format daty. Użyj: YYYY-MM-DD");
                        }
                    } else {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "renewalDate musi być stringiem w formacie YYYY-MM-DD");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Nieznane pole: " + key);
            }

        });
        return subscriptionRepository.save(subscription);
    }


    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new RuntimeException("Subskrypcja o ID " + id + " nie istnieje!");
        }
        subscriptionRepository.deleteById(id);
    }
}
