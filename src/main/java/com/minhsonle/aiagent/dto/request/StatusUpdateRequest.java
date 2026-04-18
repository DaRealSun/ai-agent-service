package com.minhsonle.aiagent.dto.request;

import com.minhsonle.aiagent.entity.ClaimStatus;
import lombok.NonNull;

public record StatusUpdateRequest(@NonNull ClaimStatus status) {
}
