package com.epam.jmp_cloud_bank_impl;

import java.util.Random;

abstract public class CardNumberGenerator {
    static String generateCardNumber() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<16; ++i) {
            int digit = r.nextInt(10);
            sb.append(digit);
        }
        return sb.toString();
    }
}
