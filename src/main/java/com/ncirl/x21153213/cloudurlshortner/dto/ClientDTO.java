package com.ncirl.x21153213.cloudurlshortner.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
/*
@author : x21153213niharika
* This is a DTo class for Client
@used: used in register client information and save
@calling classes : ClientController, ClientService and test classes
* */
public class ClientDTO {
    private long clientId;
    private String clientName;
    private String clientEmail;
    private String clientAccountNumber;
    private long totalApiHits;
    private String apiKey;
}
