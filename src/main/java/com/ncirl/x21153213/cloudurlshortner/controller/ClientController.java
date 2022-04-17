package com.ncirl.x21153213.cloudurlshortner.controller;

import com.ncirl.x21153213.cloudurlshortner.dto.ClientDTO;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/cloudurl")
public class ClientController {
    private final ClientService clientService;

    @PostMapping(path = "/registerclient")
    @ResponseBody
    public ResponseEntity<ClientDTO> registerMe(@RequestBody ClientDTO clientDTO) {
        if (!StringUtils.hasText(clientDTO.getClientName())) {
            log.error("Invalid Input Passed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        clientDTO = clientService.registerClient(clientDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }
}