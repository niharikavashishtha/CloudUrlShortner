package com.ncirl.x21153213.cloudurlshortner.controller;

import com.ncirl.x21153213.cloudurlshortner.dto.LongUrlDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import com.ncirl.x21153213.cloudurlshortner.repository.ClientRepository;
import com.ncirl.x21153213.cloudurlshortner.service.ClientService;
import com.ncirl.x21153213.cloudurlshortner.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/cloudurl")
public class ClientController {
    private final ClientService clientService;
        @PostMapping(path = "/registerclient" )
        @ResponseBody
        public ResponseEntity<ClientEntity> registerMe(@RequestBody ClientEntity clientEntity) {
            ClientEntity client = clientService.registerClient(clientEntity);
            return ResponseEntity.status(HttpStatus.OK).body(clientEntity);
        }

}