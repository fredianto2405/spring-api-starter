package dev.freddxant.api.todo.service;

import dev.freddxant.api.todo.dto.TodoRequest;
import dev.freddxant.api.todo.dto.TodoResponse;
import dev.freddxant.api.todo.entity.Todo;
import dev.freddxant.api.todo.repository.TodoRepository;
import dev.freddxant.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public void create(TodoRequest request, Authentication auth) {
        var user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var todo = Todo.builder()
                .title(request.getTitle())
                .completed(request.isCompleted())
                .user(user)
                .build();
        todoRepository.save(todo);
    }

    public List<TodoResponse> findAll(Authentication auth) {
        var user = userRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Todo> todoList = todoRepository.findAllByUserId(user.getId());

        return todoList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TodoResponse findById(Long id) {
        var todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
        return mapToResponse(todo);
    }

    public void update(Long id, TodoRequest request) {
        var todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());
        todoRepository.save(todo);
    }

    public void delete(Long id) {
        var todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
        todoRepository.delete(todo);
    }

    private TodoResponse mapToResponse(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted());
    }
}
