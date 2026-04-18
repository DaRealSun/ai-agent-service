package com.minhsonle.aiagent.dto.request;

import com.minhsonle.aiagent.entity.ClaimStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ClaimRequest(@NotBlank String claimNumber,
                           @NotBlank String patientName,
                           @Nullable String diagnosisCode,
                           @NonNull @Positive BigDecimal amount){}
