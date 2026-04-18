package com.minhsonle.aiagent.service;

import com.minhsonle.aiagent.dto.request.ClaimRequest;
import com.minhsonle.aiagent.dto.request.StatusUpdateRequest;
import com.minhsonle.aiagent.dto.response.ClaimResponse;
import com.minhsonle.aiagent.entity.ClaimStatus;

import java.util.List;

public interface ClaimService {
    ClaimResponse create(ClaimRequest request);
    ClaimResponse getById(Long id);
    List<ClaimResponse> getByStatus(ClaimStatus status);
    List<ClaimResponse> getAll();
    ClaimResponse updateStatus(Long id, StatusUpdateRequest request);
}
