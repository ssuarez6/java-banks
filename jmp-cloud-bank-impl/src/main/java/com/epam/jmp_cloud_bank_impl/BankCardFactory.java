package com.epam.jmp_cloud_bank_impl;

import com.epam.jmp_dto.*;

@FunctionalInterface
interface BankCardFactory {
    BankCard create(String cardNumber, User user);
}