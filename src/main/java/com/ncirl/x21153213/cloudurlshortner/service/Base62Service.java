package com.ncirl.x21153213.cloudurlshortner.service;

import org.springframework.stereotype.Service;

@Service
public class Base62Service {
    //private static final String BASE_62_CONTENT = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String BASE_62_CONTENT = "r6stUklcYdmnfbQSeI9XFqBgV5i0GM3ROTJxwCWjNpyK7124Do8zvhZHAuELPa";
    private final char[] BASE_62_CHAR_ARRAY = BASE_62_CONTENT.toCharArray();
    private final int BASE_62 = 62;

    public String encode(long input){
        StringBuilder encodedString = new StringBuilder();
        if(input == 0) {
            return String.valueOf(BASE_62_CHAR_ARRAY[0]);
        }
        while (input > 0) {
            encodedString.append(BASE_62_CHAR_ARRAY[(int) (input % BASE_62)]);
            input = input / BASE_62;
        }
        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        char[] characters = input.toCharArray();
        int length = characters.length;
        long decoded = 0;
        int counter = 1;
        for (int i = 0; i < length; i++) {
            decoded = decoded + (BASE_62_CONTENT.indexOf(characters[i]) * Double.valueOf(Math.pow(BASE_62, length - counter)).longValue());
            counter++;
        }
        return decoded;
    }
}
