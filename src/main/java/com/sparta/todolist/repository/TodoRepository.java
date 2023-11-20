package com.sparta.todolist.repository;

import com.sparta.todolist.entity.TodoCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoCard, Long> {
    List<TodoCard> findAllByOrderByUsernameAscCreatedAtDesc();
}
