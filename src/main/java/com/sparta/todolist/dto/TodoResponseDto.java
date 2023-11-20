package com.sparta.todolist.dto;

import com.sparta.todolist.entity.TodoCard;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean completed;

    public TodoResponseDto(TodoCard todoCard) {
        this.id = todoCard.getId();
        this.title = todoCard.getTitle();
        this.username = todoCard.getUsername();
        this.contents = todoCard.getContents();
        this.completed = todoCard.getCompleted();
        this.createdAt = todoCard.getCreatedAt();
        this.modifiedAt = todoCard.getModifiedAt();
    }
}
