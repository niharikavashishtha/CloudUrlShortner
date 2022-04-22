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
@method : used post mapping to register a client and
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
    /*
     * @Description: deleteClient method delete the registered client with the applocation
     * @param: takes clientID as an input
     * @returns: void
     * call method deleteClient of clientService class
     * do: deleted client entity and url associated with the client
     * */
    @DeleteMapping(path = "/clients/{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId) {
        clientService.deleteClient(clientId);
    }
    /*
     * @Description: get method for the registered client
     * @param: takes clientID as an input
     * @returns: ClientDto
     * call method getClient of clientService class
     * */
    @GetMapping(path = "/clients/{clientId}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable("clientId") Long clientId) {
        ClientDTO clientdto = clientService.getClient(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(clientdto);
    }
    /*
     * @Description: update method for the registered client to update the already registered clients
     * @param: takes ClientDTO object as an input
     * @returns: return ClientDto for reference
     * call method updateClient of clientservice class
     * */
    @PutMapping(path = "/clients")
    @ResponseBody
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
        if (!StringUtils.hasText(clientDTO.getClientName())) {
            log.error("Invalid Input Passed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ClientDTO clientdto = clientService.updateClient(clientDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clientdto);
    }
}