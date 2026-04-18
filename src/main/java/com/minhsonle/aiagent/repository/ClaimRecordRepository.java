package com.minhsonle.aiagent.repository;

import com.minhsonle.aiagent.entity.ClaimRecord;
import com.minhsonle.aiagent.entity.ClaimStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClaimRecordRepository extends JpaRepository<ClaimRecord, Long> {
    Optional<ClaimRecord> findByClaimNumber(String claimNumber);
    List<ClaimRecord> findByStatus(ClaimStatus status);
}
