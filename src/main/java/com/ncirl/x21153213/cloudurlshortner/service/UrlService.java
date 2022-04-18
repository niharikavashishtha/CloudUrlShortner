package com.ncirl.x21153213.cloudurlshortner.service;

import com.ncirl.x21153213.cloudurlshortner.dto.LongUrlDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import com.ncirl.x21153213.cloudurlshortner.entity.UrlEntity;
import com.ncirl.x21153213.cloudurlshortner.exception.ClientIdNotFoundException;
import com.ncirl.x21153213.cloudurlshortner.exception.InvalidApiKeyException;
import com.ncirl.x21153213.cloudurlshortner.repository.ClientRepository;
import com.ncirl.x21153213.cloudurlshortner.repository.UrlRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;


@Service
@RequiredArgsConstructor
/*
@author : x21153213niharika
* This is a Service class for URLs
@used: used getting the long url id and setting the short url string
@methods: getLongUrl, toShortUrl
@calling classes : CloudUrlController
* */
public class UrlService {
    private final UrlRepository urlRepository;
    private final ClientRepository clientRepository;

    private final Base62Service base62Service;

    @Value("${server.port:8080}")
    private int port;

    @Value("${url.prefix:127.0.0.1}")
    private String publicIp;

    @Transactional(propagation = Propagation.REQUIRED)
    /*
     * @Description: this method is getting longurl by calling decode method of Base62Service class
     * @param: takes shorturl String as input
     * @returns: actual longurl String that is associated with the short url
     * */
    public String getLongUrl(String shortUrl){
       Long id = base62Service.decode(shortUrl);
        UrlEntity urlEntity =
        urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));
                ClientEntity clientEntity = clientRepository.getById(urlEntity.getClientId());
                clientEntity.setTotalApiHits(clientEntity.getTotalApiHits()+1);
                clientRepository.save(clientEntity);

       return urlEntity.getLongUrl();
    }

    /*
     * @Description: this method is used to identify longUrl and return the shortURL in return
     * @param: takes LongUrlDTO as input from client to find the id of saved long url
     * @returns: return LongUrlDTO
     * */
    @Transactional(propagation = Propagation.REQUIRED)
    public LongUrlDTO toShortUrl(LongUrlDTO longURLDto){
        ClientEntity clientEntity = clientRepository.findById(longURLDto.getClientId()).orElseThrow(() ->
                 new ClientIdNotFoundException("Client Id is not found") );

        if( clientEntity.getApiKey().equals(longURLDto.getApiKey())) {
            UrlEntity urlEntity = UrlEntity.builder().longUrl(longURLDto.getLongUrl())
                    .createdDate(new Date()).clientId(longURLDto.getClientId()).build();
            urlEntity = urlRepository.save(urlEntity);
            longURLDto.setShortUrl("http://" + publicIp + ":" + port + "/cloudurl/"  + base62Service.encode(urlEntity.getId()));
            return longURLDto;
        } else {
            throw new InvalidApiKeyException("Invalid API Key passed");
        }
    }
}
