package com.ncirl.x21153213.cloudurlshortner.service;

import com.ncirl.x21153213.cloudurlshortner.dto.LongUrlDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.UrlEntity;
import com.ncirl.x21153213.cloudurlshortner.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @Mock
    private UrlRepository urlRepository;
    @Mock
    private Base62Service base62Service;
    @InjectMocks
    private UrlService urlService;

    @Test
    public void testGetLongUrl_success(){
        when(base62Service.decode("xyzabc")).thenReturn(123L);
        when(urlRepository.findById(123L)).thenReturn(Optional.of(UrlEntity.builder().longUrl("http://www.nueda.com").build()));
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
        when(urlRepository.save(any(UrlEntity.class))).thenReturn(UrlEntity.builder().id(123L).build());

        String shortUrl = urlService.toShortUrl(new LongUrlDTO());
        assertEquals("xyaabc", shortUrl);
    }
}