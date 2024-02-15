package com.stack.puzzle.domain.user.repository;

import com.stack.puzzle.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}