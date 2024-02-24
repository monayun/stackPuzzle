package com.stack.puzzle.domain.house.repository;

import com.stack.puzzle.domain.house.entity.Lawd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LawdRepository extends JpaRepository<Lawd, Long> {
    Optional<Lawd> findByLawdCd(String lawdCd);
}