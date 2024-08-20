package com.epam.jmp_service_api;

import com.epam.jmp_dto.BankCard;
import com.epam.jmp_dto.Subscription;
import com.epam.jmp_dto.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Service {

    void subscribe(BankCard card);

    Optional<Subscription> getSubscriptionByBankCardNumber(String number);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByPredicate(Predicate<Subscription> p);

    default double getAverageUsersAge(){
        var allUsers = getAllUsers();
        var agesSum = (double)allUsers.stream()
                .map(User::getAge)
                .reduce(0, Integer::sum);
        return agesSum / allUsers.size();
    }

    static boolean isPayableUser(User user) {
        return user.getAge() >= 18;
    }

}
