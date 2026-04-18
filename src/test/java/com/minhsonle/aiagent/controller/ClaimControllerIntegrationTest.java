package com.minhsonle.aiagent.controller;

import com.minhsonle.aiagent.dto.request.ClaimRequest;
import com.minhsonle.aiagent.repository.ClaimRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.minhsonle.aiagent.TestcontainersConfiguration;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
class ClaimControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ClaimRecordRepository repo;
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

}