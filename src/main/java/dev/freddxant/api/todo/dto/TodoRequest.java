package dev.freddxant.api.todo.dto;

import lombok.Data;

@Data
public class TodoRequest {
    private String title;
    private boolean completed;
}
