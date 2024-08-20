package com.epam.jmp_app;


import com.epam.jmp_bank_api.Bank;
import com.epam.jmp_cloud_service_impl.ServiceImpl;
import com.epam.jmp_dto.BankCardType;
import com.epam.jmp_dto.User;
import com.epam.jmp_service_api.JmpException;
import com.epam.jmp_service_api.Service;

import java.time.LocalDate;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class App {
    public static void main( String[] args ) {
        var user = new User("Santiago", "Suarez", LocalDate.of(1996, 4, 16));
        var user2 = new User("Freddie", "Mercury", LocalDate.of(1960, 3, 31));
        var user3 = new User("Matthew", "Bellamy", LocalDate.of(1979, 11, 15));
        var service = new ServiceImpl();

        ServiceLoader<Bank> serviceLoader = ServiceLoader.load(Bank.class);

        for (Bank bank: serviceLoader) {
            System.out.println("Loaded Bank implementation: " + bank.getClass().getName());
            var creditCard = bank.createBankCard(user, BankCardType.CREDIT);
            var debitCard = bank.createBankCard(user, BankCardType.DEBIT);
            service.subscribe(creditCard);
            service.subscribe(debitCard);

            var cardUser2 = bank.createBankCard(user2, BankCardType.CREDIT);
            service.subscribe(cardUser2);

            var cardUser3 = bank.createBankCard(user3, BankCardType.DEBIT);
            service.subscribe(cardUser3);

            var creditCardSubscription = service.getSubscriptionByBankCardNumber(creditCard.getNumber());
            System.out.println("Credit card subscription: " + (creditCardSubscription.isEmpty() ? "non-existent" : "existent"));
        }

        System.out.println("Average user age: " + service.getAverageUsersAge());

        var userNames = service
                .getAllUsers()
                .stream()
                .map(u -> u.getName() + " " + u.getSurname())
                .collect(Collectors.toUnmodifiableList());

        try {
            service.getSubscriptionByBankCardNumber("invalid number")
                    .orElseThrow(() -> new JmpException("Invalid card number."));
        } catch (JmpException jex) {
            System.out.println("Correctly caught JmpException");
        }

        var underAgeUser = new User("Roger", "Federer", LocalDate.of(2010, 1, 1));
        var isPayable = Service.isPayableUser(underAgeUser);
        System.out.println("Is Roger Federer a payable user? " + isPayable);

        System.out.println("all users: " + userNames);
    }
}
