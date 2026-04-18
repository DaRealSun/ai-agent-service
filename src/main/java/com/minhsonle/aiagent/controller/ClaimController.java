package com.minhsonle.aiagent.controller;

import com.minhsonle.aiagent.dto.request.ClaimRequest;
import com.minhsonle.aiagent.dto.request.StatusUpdateRequest;
import com.minhsonle.aiagent.dto.response.ApiResponse;
import com.minhsonle.aiagent.dto.response.ClaimResponse;
import com.minhsonle.aiagent.entity.ClaimStatus;
import com.minhsonle.aiagent.service.ClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/v1/claims")
@RequiredArgsConstructor
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<ApiResponse<ClaimResponse>> claim(@Valid @RequestBody ClaimRequest claimRequest) {
         var created = claimService.create(claimRequest);
         var location = ServletUriComponentsBuilder.fromCurrentRequest()
                         .path("/{id}").buildAndExpand(created.id())
                         .toUri();
         return ResponseEntity.created(location).body(ApiResponse.success(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClaimResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(claimService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClaimResponse>>> getAll(@RequestParam(required = false) ClaimStatus status){
        var result = (status!=null)
                ? claimService.getByStatus(status)
                : claimService.getAll();
        return ResponseEntity.ok(ApiResponse.success(result));

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ClaimResponse>> put(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest statusUpdateRequest){
        return ResponseEntity.ok(ApiResponse.success(claimService.updateStatus(id, statusUpdateRequest)));
    }
}
