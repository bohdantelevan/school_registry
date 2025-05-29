package com.registry.schools.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registry.schools.BaseIntegrationTest;
import com.registry.schools.DTO.SchoolDTO;
import com.registry.schools.Entity.School;
import com.registry.schools.Enums.SchoolType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

class SchoolControllerTest extends BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndListSchools() throws Exception {
        WebTestClient webClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();

        SchoolDTO dto = new SchoolDTO("API School", 999999, "Odesa", SchoolType.ЗЗСО, true);

        webClient.post().uri("/schools")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("API School");

        webClient.get().uri("/schools?region=Odesa&type=ЗЗСО&isActive=true")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Object.class)
                .hasSize(1);
    }

    @Test
    void testDeactivateSchool() throws Exception {
        WebTestClient webClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();

        SchoolDTO dto = new SchoolDTO("Deactivation School", 888888, "Dnipro", SchoolType.ЛІЦЕЙ, true);

        School createdSchool = webClient.post().uri("/schools")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(objectMapper.writeValueAsString(dto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(School.class)
                .returnResult()
                .getResponseBody();

        String id = createdSchool.getId().toString();

        webClient.patch().uri("/schools/" + id + "/deactivate")
                .exchange()
                .expectStatus().isOk();

        webClient.get().uri("/schools?region=Dnipro&type=ЛІЦЕЙ&isActive=false")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Object.class)
                .hasSize(1);
    }

}
