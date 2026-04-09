package com.minhsonle.aiagent.controller;

import com.minhsonle.aiagent.dto.request.ChatRequest;
import com.minhsonle.aiagent.dto.response.ApiResponse;
import com.minhsonle.aiagent.dto.response.ChatResponse;
import com.minhsonle.aiagent.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ApiResponse<ChatResponse>> chat(@Valid @RequestBody ChatRequest chatRequest) {
        return ResponseEntity.ok(ApiResponse.success(chatService.chat(chatRequest)));
    }
}
