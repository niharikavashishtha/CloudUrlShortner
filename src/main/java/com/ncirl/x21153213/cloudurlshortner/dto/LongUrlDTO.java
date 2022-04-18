package com.ncirl.x21153213.cloudurlshortner.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/*
@author : x21153213niharika
* This is a DTo class for URL
@used: used for saving long URL
@calling classes : CloudUrlController,  UrlService and test classes
* */
public class LongUrlDTO {
    private String longUrl;
    private long clientId;
    private String apiKey;
    private String shortUrl;
}
