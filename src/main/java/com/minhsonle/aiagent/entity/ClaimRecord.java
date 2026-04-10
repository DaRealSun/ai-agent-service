package com.minhsonle.aiagent.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "claim_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String claimNumber;

    @Column(nullable = false)
    private String patientName;

    private String diagnosisCode;

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

