package com.ncirl.x21153213.cloudurlshortner.service;

import com.ncirl.x21153213.cloudurlshortner.dto.LongUrlDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import com.ncirl.x21153213.cloudurlshortner.entity.UrlEntity;
import com.ncirl.x21153213.cloudurlshortner.repository.ClientRepository;
import com.ncirl.x21153213.cloudurlshortner.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @Mock
    private UrlRepository urlRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private Base62Service base62Service;
    @InjectMocks
    private UrlService urlService;

    @Test
    public void testGetLongUrl_success(){
        when(base62Service.decode("xyzabc")).thenReturn(123L);
        when(urlRepository.findById(123L)).thenReturn(Optional.of(UrlEntity.builder().longUrl("http://www.nueda.com").clientId(1234).build()));
        when(clientRepository.getById(1234L)).thenReturn(new ClientEntity());
        String longUrl = urlService.getLongUrl("xyzabc");
        assertEquals("http://www.nueda.com", longUrl);
    }

    @Test
    public void testGetLongUrl_NotFound(){
        when(base62Service.decode("xyzabc")).thenReturn(123L);
        when(urlRepository.findById(123L)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            String longUrl = urlService.getLongUrl("xyzabc");
        } );
    }

    @Test
    public void testToShortUrl_success(){
        when(base62Service.encode(123L)).thenReturn("xyaabc");
        when(urlRepository.save(any(UrlEntity.class))).thenReturn(UrlEntity.builder().id(123L).clientId(345L).build());

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setApiKey("something");
        when(clientRepository.findById(345L)).thenReturn(Optional.of(clientEntity));

        LongUrlDTO longUrlDTO = new LongUrlDTO();
        longUrlDTO.setLongUrl("anything.com");
        longUrlDTO.setApiKey("something");
        longUrlDTO.setClientId(345L);
        LongUrlDTO shortUrl = urlService.toShortUrl(longUrlDTO);
        assertTrue(shortUrl.getShortUrl().endsWith("/cloudurl/xyaabc"));
    }
}