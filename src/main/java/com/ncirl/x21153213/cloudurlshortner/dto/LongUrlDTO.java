package com.ncirl.x21153213.cloudurlshortner.dto;

import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
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
public class LongUrlDTO {
    private String longUrl;
    private long clientId;
    private String apiKey;
    private String shortUrl;
}
