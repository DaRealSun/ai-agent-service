package com.minhsonle.aiagent.dto.request;

import jakarta.validation.constraints.NotBlank;
public record ChatRequest(@NotBlank(message = "message cannot be empty") String message) {
}
