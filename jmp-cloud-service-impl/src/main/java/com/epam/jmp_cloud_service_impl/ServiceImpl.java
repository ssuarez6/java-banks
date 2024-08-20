package com.epam.jmp_cloud_service_impl;

import com.epam.jmp_dto.BankCard;
import com.epam.jmp_dto.Subscription;
import com.epam.jmp_dto.User;
import com.epam.jmp_service_api.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {
    private Map<User, List<Subscription>> subscriptionsByUser;
    
    public ServiceImpl(){
        subscriptionsByUser = new HashMap<>();
    }

    @Override
    public void subscribe(BankCard card) {
        var today = LocalDate.now();
        var subscription = new Subscription(card.getNumber(), today);
        var user = card.getUser();
        if(subscriptionsByUser.containsKey(user)) {
            var subs = subscriptionsByUser.get(user);
            subs.add(subscription);
        } else {
            var subList = new ArrayList<Subscription>();
            subList.add(subscription);
            subscriptionsByUser.put(user, subList);
        }
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return subscriptionsByUser.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .filter(sub -> sub.getBankcardNumber().equals(number))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return subscriptionsByUser
                .keySet()
                .stream()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByPredicate(Predicate<Subscription> p) {
        return subscriptionsByUser
                .values()
                .stream()
                .flatMap(Collection::stream)
                .filter(p)
                .collect(Collectors.toUnmodifiableList());
    }
}
