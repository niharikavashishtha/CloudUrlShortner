package com.ncirl.x21153213.cloudurlshortner.service;

import com.ncirl.x21153213.cloudurlshortner.dto.LongUrlDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import com.ncirl.x21153213.cloudurlshortner.entity.UrlEntity;
import com.ncirl.x21153213.cloudurlshortner.repository.ClientRepository;
import com.ncirl.x21153213.cloudurlshortner.repository.UrlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;


@Service
@AllArgsConstructor
public class UrlService {
    private UrlRepository urlRepository;
    private ClientRepository clientRepository;

    private Base62Service base62Service;

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

    public String toShortUrl(LongUrlDTO longURLDto){
        ClientEntity clientEntity = clientRepository.findById(longURLDto.getClientId()).orElseThrow(() ->
                 new EntityNotFoundException("Client Id is not found") );

        if( clientEntity.getApiKey().equals(longURLDto.getApiKey())) {
            UrlEntity urlEntity = UrlEntity.builder().longUrl(longURLDto.getLongUrl())
                    .createdDate(new Date()).clientId(longURLDto.getClientId()).build();
            urlEntity = urlRepository.save(urlEntity);
            return base62Service.encode(urlEntity.getId());
        } else {
            throw new IllegalArgumentException("Invalid API Key passed");
        }
    }
}
