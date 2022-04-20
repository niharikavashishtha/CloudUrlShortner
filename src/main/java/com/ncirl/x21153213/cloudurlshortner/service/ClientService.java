package com.ncirl.x21153213.cloudurlshortner.service;

import com.ncirl.x21153213.cloudurlshortner.dto.ClientDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import com.ncirl.x21153213.cloudurlshortner.entity.UrlEntity;
import com.ncirl.x21153213.cloudurlshortner.exception.ClientIdNotFoundException;
import com.ncirl.x21153213.cloudurlshortner.repository.ClientRepository;
import com.ncirl.x21153213.cloudurlshortner.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final UrlRepository urlRepository;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteClient(Long clientId)
    {
        urlRepository.deleteAllByClientId(clientId);
        clientRepository.deleteById(clientId);

    }

    public ClientDTO getClient(Long clientId)
    {
        ClientEntity clientEntity = clientRepository.findById(clientId)
                .orElseThrow(()-> new EntityNotFoundException("Client id not found"));
        ClientDTO clientDTO = ClientDTO.builder().clientName(clientEntity.getClientName())
                .clientEmail(clientEntity.getClientEmail())
                .clientId(clientEntity.getClientId())
                .clientAccountNumber(clientEntity.getClientAccountNumber()).build();
        return clientDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ClientDTO updateClient(ClientDTO clientDTO){
        ClientEntity clientEntity = clientRepository.findById(clientDTO.getClientId())
                .orElseThrow(()-> new EntityNotFoundException("Client id not found"));
        if(!clientEntity.getApiKey().equals(clientDTO.getApiKey())) {
            throw new ClientIdNotFoundException("Provided api key and client id do not match");
        }
        ClientEntity updatedClientEntity = ClientEntity.builder().clientName(clientDTO.getClientName())
                .clientEmail(clientDTO.getClientEmail()).clientAccountNumber(clientDTO.getClientAccountNumber())
                .apiKey(clientDTO.getApiKey()).clientId(clientDTO.getClientId()).build();
        clientRepository.saveAndFlush(updatedClientEntity);
        return clientDTO;
    }
}
