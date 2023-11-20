package com.sparta.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Pattern(regexp = "^[a-z0-9]+$", message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9) 로 구성되어야 합니다.")
    @Size(min = 4, max = 10)
    @NotBlank
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.")
    @Size(min = 8, max = 15)
    @NotBlank
    private String password;

}