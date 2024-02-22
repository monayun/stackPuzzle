package com.stack.puzzle.domain.user.controller;

import com.stack.puzzle.domain.user.entity.User;
import com.stack.puzzle.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
