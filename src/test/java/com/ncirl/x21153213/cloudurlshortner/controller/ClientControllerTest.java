package com.ncirl.x21153213.cloudurlshortner.controller;

import com.ncirl.x21153213.cloudurlshortner.dto.ClientDTO;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClientException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles({"local", "test", "h2"})
public class ClientControllerTest {
    @LocalServerPort
    private int port;

    @Value("${url.prefix:127.0.0.1}")
    private String publicIp;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(0)
    public void test_post_client() throws Exception {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName("X21153213");

        ResponseEntity<ClientDTO> returnValue = this.restTemplate.postForEntity("http://localhost:" +
                port + "/cloudurl/registerclient", clientDTO, ClientDTO.class);
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnValue.getBody().getApiKey()).isNotEmpty();
        assertThat(returnValue.getBody().getClientName()).isEqualTo("X21153213");
    }
    @Test
    @Order(1)
    public void test_post_null_name_client() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName(null);
        ResponseEntity<ClientDTO> returnClientNameValue = this.restTemplate.postForEntity("http://localhost:" +
                port + "/cloudurl/registerclient", clientDTO, ClientDTO.class);
        assertThat(returnClientNameValue.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    @Order(2)
    public void test_get_client() throws Exception {
        long clientIdGenerated =1;
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName("X21153213");
            ResponseEntity<ClientDTO> returnValue = this.restTemplate.postForEntity("http://localhost:" +
                    port + "/cloudurl/registerclient", clientDTO, ClientDTO.class);
            clientIdGenerated = returnValue.getBody().getClientId();
        returnValue = this.restTemplate.getForEntity("http://localhost:" +
                port + "/cloudurl/clients/" + clientIdGenerated, ClientDTO.class);
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnValue.getBody().getClientId()).isEqualTo(clientIdGenerated);
        //assertThat(returnValueforget.getBody().getApiKey()).isNotEmpty();
        assertThat(returnValue.getBody().getClientName()).isEqualTo("X21153213");
    }
    @Test
    @Order(3)
    public void test_delete_client() throws Exception {
        long clientIdGenerated =1;
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName("X21153213");
        ResponseEntity<ClientDTO> returnValue = this.restTemplate.postForEntity("http://localhost:" +
                port + "/cloudurl/registerclient", clientDTO, ClientDTO.class);
        clientIdGenerated = returnValue.getBody().getClientId();
        this.restTemplate.delete("http://localhost:" +
                port + "/cloudurl/clients/" +clientIdGenerated);

        boolean isExceptionThrown = false;
        try {
            ResponseEntity responseEntity = this.restTemplate.getForEntity("http://localhost:" +
                    port + "/cloudurl/clients/" + clientIdGenerated, ClientDTO.class);
        } catch (RestClientException restClientException) {
            isExceptionThrown = true;
        }
        assertThat(isExceptionThrown).isTrue();
    }
    @Test
    @Order(4)
    public void test_put_client() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName("X21153213");

        ResponseEntity<ClientDTO> returnValue = this.restTemplate.postForEntity("http://localhost:" +
                port + "/cloudurl/registerclient", clientDTO, ClientDTO.class);
        clientDTO.setClientId(returnValue.getBody().getClientId());
        clientDTO.setClientName(returnValue.getBody().getClientName()+"Niha");
        clientDTO.setApiKey(returnValue.getBody().getApiKey());
        clientDTO.setClientAccountNumber(returnValue.getBody().getClientAccountNumber());

         this.restTemplate.put("http://localhost:" +
                port + "/cloudurl/clients", clientDTO, ClientDTO.class);
         returnValue = this.restTemplate.getForEntity("http://localhost:" +
                 port + "/cloudurl/clients/"+clientDTO.getClientId(), ClientDTO.class);
         assertThat(returnValue.getBody().getClientName()).isEqualTo("X21153213Niha");

    }
}
