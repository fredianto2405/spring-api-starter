package dev.freddxant.api.auth.controller;

import dev.freddxant.api.auth.dto.AuthResponse;
import dev.freddxant.api.auth.dto.RegisterRequest;
import dev.freddxant.api.auth.service.AuthService;
import dev.freddxant.api.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseUtil.success("Register successful");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
