package com.ncirl.x21153213.cloudurlshortner.controller;

import com.ncirl.x21153213.cloudurlshortner.dto.ClientDTO;
import com.ncirl.x21153213.cloudurlshortner.dto.LongUrlDTO;
import com.ncirl.x21153213.cloudurlshortner.entity.ClientEntity;
import org.junit.jupiter.api.Disabled;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles({"local", "test", "h2"})
public class CloudUrlControllerTest {
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
    public void test_post_short_me_url() throws Exception {

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientName("X21153213");

        ResponseEntity<ClientDTO> returnValue = this.restTemplate.postForEntity("http://localhost:" +
                port + "/cloudurl/registerclient", clientDTO, ClientDTO.class);
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.OK);

        LongUrlDTO longURLDto = new LongUrlDTO();
        longURLDto.setClientId(returnValue.getBody().getClientId());
        longURLDto.setApiKey(returnValue.getBody().getApiKey());

        longURLDto.setLongUrl("http://www.neueda.com/something/exciting/going/to/happen/with/this/long/url");
        longURLDto.setClientId(returnValue.getBody().getClientId());
        ResponseEntity<LongUrlDTO> returnLongUrl = this.restTemplate.postForEntity("http://127.0.0.1:" + port + "/cloudurl/short-me", longURLDto, LongUrlDTO.class);
        assertThat(returnLongUrl.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnLongUrl.getBody()).isInstanceOf(LongUrlDTO.class);

        assertThat(returnLongUrl.getBody().getShortUrl()).isEqualTo("http://" + publicIp + ":" + port + "/cloudurl/sqFWx0");
    }


    @Test
    @Order(2)
    public void test_post_short_me_url_null_url_request() throws Exception {
        LongUrlDTO longURLDto = new LongUrlDTO();
        longURLDto.setLongUrl(null);
        ResponseEntity<String> returnValue = this.restTemplate.postForEntity("http://localhost:" + port + "/cloudurl/short-me", longURLDto, String.class);
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Order(3)
    public void test_post_short_me_url_empty_url_request() throws Exception {
        LongUrlDTO longURLDto = new LongUrlDTO();
        longURLDto.setLongUrl("");
        ResponseEntity<String> returnValue = this.restTemplate.postForEntity("http://localhost:" + port + "/cloudurl/short-me", longURLDto, String.class);
        assertThat(returnValue.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Order(4)
    @Disabled("ignoring to fix nginx routing")
    public void test_get_me_url() throws Exception {
        LongUrlDTO longURLDto = new LongUrlDTO();
        longURLDto.setLongUrl("http://www.neueda.com/something/exciting/going/to/happen/with/this/long/url");
        ResponseEntity<String> returnPostValue = this.restTemplate.postForEntity("http://localhost:" + port + "/cloudurl/short-me", longURLDto, String.class);

        ResponseEntity<Void> returnGetValue = this.restTemplate.getForEntity( returnPostValue.getBody(), Void.class );
        assertThat(returnGetValue.getStatusCode()).isEqualTo(HttpStatus.FOUND);
    }

    @Test
    @Order(5)
    public void test_get_me_url_empty_short_url_passed() throws Exception {
        ResponseEntity<Void> returnGetValue = this.restTemplate.getForEntity("http://localhost:" + port + "/cloudurl/" + " ", Void.class);
        assertThat(returnGetValue.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Order(6)
    public void test_get_me_url_empty_short_url_not_present() throws Exception {
        ResponseEntity<Void> returnGetValue = this.restTemplate.getForEntity("http://localhost:" + port + "/cloudurl/" + "missing-short-code", Void.class);
        assertThat(returnGetValue.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}