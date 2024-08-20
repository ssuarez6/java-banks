package com.epam.jmp_bank_api;

import com.epam.jmp_dto.BankCard;
import com.epam.jmp_dto.BankCardType;
import com.epam.jmp_dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType type);
}
