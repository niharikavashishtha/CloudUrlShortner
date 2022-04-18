package com.ncirl.x21153213.cloudurlshortner.controller;

import com.ncirl.x21153213.cloudurlshortner.dto.ClientDTO;
import com.ncirl.x21153213.cloudurlshortner.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/cloudurl")
/*
@author : x21153213niharika
* This is a controller class for client
@method : used post mapping to register a client
* */
public class ClientController {
    private final ClientService clientService;

    @PostMapping(path = "/registerclient")
    @ResponseBody
    /*
    * @Description: register me method register the client
    * @param: takes ClientDto as an input
    * @returns: updated ClientDTO
    * */
    public ResponseEntity<ClientDTO> registerMe(@RequestBody ClientDTO clientDTO) {
        if (!StringUtils.hasText(clientDTO.getClientName())) {
            log.error("Invalid Input Passed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        clientDTO = clientService.registerClient(clientDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clientDTO);
    }
}