package com.minhsonle.aiagent.service.impl;

import com.minhsonle.aiagent.dto.request.ClaimRequest;
import com.minhsonle.aiagent.dto.request.StatusUpdateRequest;
import com.minhsonle.aiagent.dto.response.ClaimResponse;
import com.minhsonle.aiagent.entity.ClaimRecord;
import com.minhsonle.aiagent.entity.ClaimStatus;
import com.minhsonle.aiagent.exception.ResourceNotFoundException;
import com.minhsonle.aiagent.repository.ClaimRecordRepository;
import com.minhsonle.aiagent.service.ClaimService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClaimServiceImpl implements ClaimService {
    private final ClaimRecordRepository repo;


    @Transactional
    @Override
    public ClaimResponse create(ClaimRequest request) {

        ClaimRecord entity = ClaimRecord.builder().
                claimNumber(request.claimNumber()).
                patientName(request.patientName()).
                diagnosisCode(request.diagnosisCode()).
                amount(request.amount()).status(ClaimStatus.PENDING).
                createdAt(LocalDateTime.now()).
                build();

        ClaimRecord saved = repo.save(entity);
        return ClaimResponse.fromEntity(saved);
    }

    @Override
    public ClaimResponse getById(Long id) {
        return repo.findById(id)
                .map(ClaimResponse::fromEntity)
                .orElseThrow(()-> new ResourceNotFoundException("Claim not found with id : " + id));
    }

    @Override
    public List<ClaimResponse> getByStatus(ClaimStatus status) {
        return repo.findByStatus(status)
                .stream()
                .map(ClaimResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClaimResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(ClaimResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClaimResponse updateStatus(Long id, StatusUpdateRequest request) {
        var entity =  repo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Claim not found with id : " + id));
        if(!isValidTransition(entity.getStatus(),request.status())){
            throw new IllegalStateException(
                    "Cannot transition from" + entity.getStatus() + " to " + request.status()
            );
        }
        entity.setStatus(request.status());
        entity.setUpdatedAt(LocalDateTime.now());
        var saved = repo.save(entity);
        return ClaimResponse.fromEntity(saved);
    }

    private boolean isValidTransition(ClaimStatus current, ClaimStatus next){
        Map<ClaimStatus, List<ClaimStatus>> allowed =
                Map.of(ClaimStatus.PENDING,
                List.of(ClaimStatus.APPROVED,ClaimStatus.DENIED,ClaimStatus.UNDER_REVIEW),
                ClaimStatus.UNDER_REVIEW, List.of(ClaimStatus.APPROVED,ClaimStatus.DENIED),
                ClaimStatus.APPROVED, List.of(),
                ClaimStatus.DENIED, List.of()
        );
        return allowed.get(current).contains(next);
    }
}
