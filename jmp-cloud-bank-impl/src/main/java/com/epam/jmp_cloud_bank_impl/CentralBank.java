package com.epam.jmp_cloud_bank_impl;

import com.epam.jmp_bank_api.Bank;
import com.epam.jmp_dto.*;

public class CentralBank implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        switch (type) {
            case DEBIT:
                return new DebitBankCard(CardNumberGenerator.generateCardNumber(), user);
            case CREDIT:
                return new CreditBankCard(CardNumberGenerator.generateCardNumber(), user);
            default:
                throw new IllegalArgumentException("Unknown card type");
        }
    }
}
