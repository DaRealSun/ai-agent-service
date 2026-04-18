package com.minhsonle.aiagent.dto.response;

import com.minhsonle.aiagent.entity.ClaimRecord;
import com.minhsonle.aiagent.entity.ClaimStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ClaimResponse(Long id,
                            String claimNumber,
                            String patientName,
                            String diagnosisCode,
                            BigDecimal amount,
                            ClaimStatus status,
                            LocalDateTime createdAt,
                            LocalDateTime updatedAt
                           ) {
    public static ClaimResponse fromEntity(ClaimRecord entity){
        return new ClaimResponse(
                entity.getId(),
                entity.getClaimNumber(),
                entity.getPatientName(),
                entity.getDiagnosisCode(),
                entity.getAmount(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
