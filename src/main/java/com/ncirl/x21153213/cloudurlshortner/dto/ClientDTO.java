package com.ncirl.x21153213.cloudurlshortner.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {
    private long clientId;
    private String clientName;
    private String clientEmail;
    private String clientAccountNumber;
    private long totalApiHits;
    private String apiKey;
}
