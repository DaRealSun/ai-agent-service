package com.minhsonle.aiagent.controller;

import com.minhsonle.aiagent.dto.request.LoginRequest;
import com.minhsonle.aiagent.dto.response.ApiResponse;
import com.minhsonle.aiagent.dto.response.LoginResponse;
import com.minhsonle.aiagent.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    @PostMapping("/login")
    ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest){
        var token = jwtService.generateToken(loginRequest.username());
        return ResponseEntity.ok(ApiResponse.success(new LoginResponse(token)));
    }
}
