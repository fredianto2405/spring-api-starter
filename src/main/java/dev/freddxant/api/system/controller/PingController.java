package dev.freddxant.api.system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
public class PingController {
    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok(
                Map.of("pong", Instant.now().toString())
        );
    }
}
