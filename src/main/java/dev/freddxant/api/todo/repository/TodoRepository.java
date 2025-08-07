package dev.freddxant.api.todo.repository;

import dev.freddxant.api.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query(value = "select * from todos where user_id = ?1 order by created_at desc", nativeQuery = true)
    List<Todo> findAllByUserId(Long userId);
}
