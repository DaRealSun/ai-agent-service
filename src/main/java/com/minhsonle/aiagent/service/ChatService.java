package com.minhsonle.aiagent.service;

import com.minhsonle.aiagent.dto.request.ChatRequest;
import com.minhsonle.aiagent.dto.response.ChatResponse;

public interface ChatService {
    ChatResponse chat(ChatRequest request);

}
