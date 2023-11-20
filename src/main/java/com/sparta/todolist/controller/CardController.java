package com.sparta.todolist.controller;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.security.UserDetailsImpl;
import com.sparta.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {
    private final TodoService todoService;

    @PostMapping("/todo")
    public ResponseEntity<TodoResponseDto> createCard(@RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(todoService.createTodoCard(requestDto, userDetails.getUser()));
    }

    @GetMapping("/todo")
    public ResponseEntity<List<TodoResponseDto>> getCards(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(todoService.getTodoCardList());
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<TodoResponseDto> getCard(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.getTodoCard(id));
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<TodoResponseDto> updateTodoCard(@PathVariable Long id, @RequestBody TodoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(todoService.updateTodoCard(id, requestDto, userDetails.getUser()));
    }

    @PutMapping("/todo/complete/{id}")
    public TodoResponseDto completeTdoCard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return todoService.completeTodoCard(id, userDetails.getUser());
    }
}
