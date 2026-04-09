package com.minhsonle.aiagent.service.impl;

import com.minhsonle.aiagent.dto.request.ChatRequest;
import com.minhsonle.aiagent.dto.response.ChatResponse;
import com.minhsonle.aiagent.service.ChatService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional()

public class ChatServiceImpl implements ChatService {
    @Override
    public ChatResponse chat(ChatRequest request) {
        return new ChatResponse("echo: " + request.message());
    }
}
