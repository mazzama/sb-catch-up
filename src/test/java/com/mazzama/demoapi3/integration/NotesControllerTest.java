package com.mazzama.demoapi3.integration;

import com.mazzama.demoapi3.DemoApi3Application;
import com.mazzama.demoapi3.Notes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest(classes = DemoApi3Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@TestPropertySource(locations = "/application-test.yaml")
class NotesControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testPost() {
        Notes request = new Notes();
        request.setText("Test 1");
        request.setTitle("Title 1");

        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:" + port + "/api/notes", request, String.class);
        assert(response.getStatusCode().is2xxSuccessful());
        assert(response.getBody().equals("Success"));
    }

    @Test
    void getAllNews() {
        ResponseEntity<List> responses = restTemplate.getForEntity("http://localhost:" + port + "/api/notes", List.class);
        assert(responses.getStatusCode().is2xxSuccessful());
        assertThat(responses.getBody().size(), greaterThan(0));
    }
}