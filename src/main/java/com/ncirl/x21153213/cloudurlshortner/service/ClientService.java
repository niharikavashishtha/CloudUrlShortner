package com.ncirl.x21153213.cloudurlshortner.service;

import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import com.ncirl.x21153213.cloudurlshortner.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ClientEntity registerClient(ClientEntity clientEntity){
        clientEntity = clientRepository.saveAndFlush(clientEntity);

        return clientRepository.findById(clientEntity.getClientId()).get();
    }
}
