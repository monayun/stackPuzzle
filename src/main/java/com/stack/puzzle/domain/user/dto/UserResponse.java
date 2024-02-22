package com.stack.puzzle.domain.user.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResponse {
    private long seq;
    private String userId;
    private String name;
}
