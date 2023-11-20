package com.sparta.todolist.service;

import com.sparta.todolist.dto.SignupRequestDto;
import com.sparta.todolist.entity.User;
import com.sparta.todolist.entity.UserRoleEnum;
import com.sparta.todolist.jwt.JwtUtil;
import com.sparta.todolist.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }


        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        // 사용자 등록
        User user = new User(username, password, role);
        userRepository.save(user);
    }
}