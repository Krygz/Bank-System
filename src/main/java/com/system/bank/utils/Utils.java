package com.system.bank.utils;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class Utils {

    private static final Random random = new Random();

    public static String generateCardNumber(){
        return generateRandomNumber(16);
    }
    public static String generateCvv(){
        return generateRandomNumber(3);
    }
    private static String generateRandomNumber(int len){
        StringBuilder number = new StringBuilder();
        for(int i = 0;i < len;i++){
            int digit = random.nextInt(10);
            number.append(digit);
        }
        return number.toString();
    }
}
