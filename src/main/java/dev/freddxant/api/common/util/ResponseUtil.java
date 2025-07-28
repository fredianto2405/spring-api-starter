package dev.freddxant.api.common.util;

import dev.freddxant.api.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    private ResponseUtil() {}

    public static <T>ResponseEntity<ApiResponse<T>> success(String message) {
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .build()
        );
    }

    public static <T>ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .build()
        );
    }

    public static <T>ResponseEntity<ApiResponse<T>> success(String message, T data, Object meta) {
        return ResponseEntity.ok(
                ApiResponse.<T>builder()
                        .success(true)
                        .message(message)
                        .data(data)
                        .meta(meta)
                        .build()
        );
    }
}
