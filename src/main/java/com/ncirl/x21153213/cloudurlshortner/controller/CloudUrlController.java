package com.ncirl.x21153213.cloudurlshortner.controller;

import com.ncirl.x21153213.cloudurlshortner.dto.LongUrlDTO;
import com.ncirl.x21153213.cloudurlshortner.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/cloudurl")
/*
@author : x21153213niharika
* This is a controller class for URL
@method : use post mapping to save the longURL post by the client
and get mapping to give response as a short url
* */
public class CloudUrlController {

    private final UrlService urlService;

    @Value("${cloudurl.api.longUrlMaxSize:2097152}")
    private int maxURLSize;

    @Value("${server.port:8080}")
    private int port;

    @Value("${url.prefix:127.0.0.1}")
    private String publicIp;
    
    @PostMapping(path = "/short-me" )
    @ResponseBody
    /*
     * @Description: short me method takes the long url from client
     * @param: takes LongDTO as an input
     * @returns: updated LongUrlDTO
     * */
    public ResponseEntity<LongUrlDTO> shortMe(@RequestBody LongUrlDTO longURLDto) {
        if (!StringUtils.hasText(longURLDto.getLongUrl())) {
            log.error("Invalid Input Passed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (longURLDto.getLongUrl().getBytes().length > maxURLSize) {
            log.error("Long URL is longer than {}", maxURLSize);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        log.info("Received URL shortening request for {}", longURLDto.getLongUrl());
        return ResponseEntity.status(HttpStatus.OK).body(urlService.toShortUrl(longURLDto));
    }

    /*
     * @Description: getMe gives shorturl as an output for the longurl passed by client
     * */
    @GetMapping(path = "/{shortUrl}")
    public ResponseEntity<Void> getMe(@PathVariable ("shortUrl") String shortUrl) {
        if (!StringUtils.hasText(shortUrl)) {
            log.error("Invalid Input Passed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        log.info("Received URL get request for short url {}", shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.getLongUrl(shortUrl)))
                .build();
    }
}