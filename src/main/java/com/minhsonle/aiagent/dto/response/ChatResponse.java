package com.minhsonle.aiagent.dto.response;

import java.time.Instant;

public record ChatResponse(String reply, Instant timestamp) {
    public ChatResponse(String reply){
        this(reply, Instant.now());
    }
}
