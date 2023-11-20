package com.sparta.todolist.service;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.entity.TodoCard;
import com.sparta.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sparta.todolist.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    private TodoCard findTodocard(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(("선택한 카드는 존재하지 않습니다.")));
    }
    public TodoResponseDto createTodoCard(TodoRequestDto requestDto, User user) {
        TodoCard todoCard = new TodoCard(requestDto, user);
        TodoCard saveCard = todoRepository.save(todoCard);
        return new TodoResponseDto(saveCard);
    }

    public TodoResponseDto getTodoCard(Long id) {
        return new TodoResponseDto(findTodocard(id));
    }
    public List<TodoResponseDto> getTodoCardList() {
        return todoRepository.findAllByOrderByUsernameAscCreatedAtDesc().stream().map(TodoResponseDto::new).toList();
    }

    @Transactional
    public TodoResponseDto updateTodoCard(Long id, TodoRequestDto requestDto, User user) {
        TodoCard todoCard = findTodocard(id);
        if (todoCard.getUsername().equals(user.getUsername())) {
            todoCard.update(requestDto);
        }
        else {
            throw new IllegalArgumentException("작성자가 아닙니다. 작성자만 수정할 수 있습니다");
        }
        return new TodoResponseDto(todoCard);
    }

    @Transactional
    public TodoResponseDto completeTodoCard(Long id, User user) {
        TodoCard todoCard = findTodocard(id);
        if (todoCard.getUsername().equals(user.getUsername())) {
            todoCard.complete(true);
            return new TodoResponseDto(todoCard);
        }
        else{
            throw new IllegalArgumentException("작성자만 할 수 있습니다.");
        }
    }
}
