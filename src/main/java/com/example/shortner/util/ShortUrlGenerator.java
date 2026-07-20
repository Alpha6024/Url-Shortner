package com.example.shortner.util;
import java.time.LocalDateTime;
import java.security.SecureRandom;

public class ShortUrlGenerator{
    private static final String Character="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static final SecureRandom random=new SecureRandom();
    public static String generate(int length){
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            int index=random.nextInt(Character.length());
            builder.append(Character.charAt(index));
        }
        LocalDateTime now = LocalDateTime.now();
        builder.append(String.format("%02d", now.getMonthValue()));
        int milli = now.getNano() / 1_000_000;
        builder.append(String.format("%03d", milli));
        return builder.toString();
    }
}