package com.minhsonle.aiagent.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhsonle.aiagent.dto.request.ClaimRequest;
import com.minhsonle.aiagent.repository.ClaimRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.minhsonle.aiagent.TestcontainersConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class ClaimControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ClaimRecordRepository repo;
    @Autowired
    private ObjectMapper mapper;


    @BeforeEach
    void cleanup(){
        repo.deleteAll();
    }
    @Test
    void createClaim_return201(){
        var claim = new ClaimRequest("CLM-TEST-001", "Test Patient", "J06.9", new BigDecimal("1500.00"));
        var response = restTemplate.postForEntity("/api/v1/claims", claim, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    void createClaim_sameIdempotencyKey_returnsSameRecord() throws JsonProcessingException {
        var claim1 = new ClaimRequest("CLM-TEST-001", "Test Patient", "J06.9", new BigDecimal("1500.00"));
        var headers = new HttpHeaders();
        headers.set("Idempotency-Key", "test-key-001");
        var entity1= new HttpEntity<>(claim1,headers);
        var response1 = restTemplate.exchange("/api/v1/claims", POST, entity1, String.class);
        var id1 = mapper.readTree(response1.getBody()).get("data").get("id").asLong();

        var claim2 = new ClaimRequest("CLM-TEST-001", "Test Patient", "J06.9", new BigDecimal("1500.00"));
        var entity2= new HttpEntity<>(claim2,headers);
        var response2 = restTemplate.exchange("/api/v1/claims", POST, entity2, String.class);
        var id2 = mapper.readTree(response2.getBody()).get("data").get("id").asLong();

        assertEquals(id1, id2);
        assertEquals(1L, repo.count());
    }

}