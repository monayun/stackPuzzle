package com.stack.puzzle.domain.house.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LawdType {
    LAWD_CD("lawdCd", "법정 코드"),
    LAWD_DONG("lawdDong", "법정동명"),
    EXIST("exist", "존재"),
    EXIST_TRUE("존재", "존재유무");

    // 타입
    private final String name;
    // 설명
    private final String description;

}
