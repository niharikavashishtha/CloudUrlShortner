package com.ncirl.x21153213.cloudurlshortner.service;

import org.springframework.stereotype.Service;

@Service
/*
@author : x21153213niharika
* This is a Service class for generating a unique url for client's longurl request
@used: used for encoding and decoding the url for UrlService
@methods: encode, decode
@calling classes : UrlService
* */
public class Base62Service {
    private static final String BASE_62_CONTENT = "r6stUklcYdmnfbQSeI9XFqBgV5i0GM3ROTJxwCWjNpyK7124Do8zvhZHAuELPa";
    private final char[] BASE_62_CHAR_ARRAY = BASE_62_CONTENT.toCharArray();
    private final int BASE_62 = 62;
    /*
     * @Description: this encode the id it gets in input
     * @param: takes id from  as an input
     * @returns: converted string using the logic below
     * output generating from base62
     * */
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
    /*
     * @Description: this decode the string it gets in input
     * @param: takes string from UrlService.getLongUrl method as an input
     * @returns: decoded long id to client
     * */
    public long decode(String input) {
        char[] characters = input.toCharArray();
        int length = characters.length;
        long decoded = 0;
        long counter = 1;
        for (int i = 0; i < length; i++) {
            decoded = (long) (decoded + (BASE_62_CONTENT.indexOf(characters[i]) * Math.pow(BASE_62, length - counter)));
            counter++;
        }
        return decoded;
    }
}
