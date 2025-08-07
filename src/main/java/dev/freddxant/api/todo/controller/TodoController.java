package dev.freddxant.api.todo.controller;

import dev.freddxant.api.common.util.ResponseUtil;
import dev.freddxant.api.todo.dto.TodoRequest;
import dev.freddxant.api.todo.dto.TodoResponse;
import dev.freddxant.api.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TodoRequest request, Authentication auth) {
        todoService.create(request, auth);
        return ResponseUtil.success("Todo created successfully");
    }

    @GetMapping
    public ResponseEntity<?> get(Authentication auth) {
        List<TodoResponse> list = todoService.findAll(auth);
        return ResponseUtil.success("Todo retrieved successfully", list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var todoResponse = todoService.findById(id);
        return ResponseUtil.success("Todo retrieved successfully", todoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TodoRequest request) {
        todoService.update(id, request);
        return ResponseUtil.success("Todo updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseUtil.success("Todo deleted successfully");
    }
}
