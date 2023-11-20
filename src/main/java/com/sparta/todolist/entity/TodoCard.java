package com.sparta.todolist.entity;

import com.sparta.todolist.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoCard extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "completed", nullable = false)
    private Boolean completed = false;


    public TodoCard(TodoRequestDto todoRequestDto, User user) {
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.username = user.getUsername();
    }

    public void update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void complete(boolean flag) {
        this.completed = flag;
    }
}
