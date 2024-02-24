package com.stack.puzzle.domain.house.dto;

import com.stack.puzzle.domain.house.entity.Lawd;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LawdRequest {
    private Long lawdId;
    private String lawdCd;
    private String lawdDong;
    private Boolean exist;

    /**
     * DTO -> ENTITY로 변환하기
     * @return
     */
    public Lawd toEntity() {
        return Lawd.builder()
              .lawdId(lawdId)
              .lawdCd(lawdCd)
              .lawdDong(lawdDong)
              .exist(exist)
              .build();
    }
}
