package com.ncirl.x21153213.cloudurlshortner.service;

import com.ncirl.x21153213.cloudurlshortner.dto.ClientDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import com.ncirl.x21153213.cloudurlshortner.entity.UrlEntity;
import com.ncirl.x21153213.cloudurlshortner.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ClientDTO registerClient(ClientDTO clientDTO){
        ClientEntity clientEntity = ClientEntity.builder().clientName(clientDTO.getClientName())
                .clientEmail(clientDTO.getClientEmail()).clientAccountNumber(clientDTO.getClientAccountNumber())
                .apiKey(UUID.randomUUID().toString()).build();
        clientEntity = clientRepository.saveAndFlush(clientEntity);
        clientDTO.setClientId(clientEntity.getClientId());
        clientDTO.setApiKey(clientEntity.getApiKey());

        return clientDTO;
    }
}
