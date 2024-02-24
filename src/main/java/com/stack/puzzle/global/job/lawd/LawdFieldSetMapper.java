package com.stack.puzzle.global.job.lawd;

import com.stack.puzzle.domain.house.consts.LawdType;
import com.stack.puzzle.domain.house.dto.LawdRequest;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class LawdFieldSetMapper implements FieldSetMapper<LawdRequest> {

    @Override
    public LawdRequest mapFieldSet(FieldSet fieldSet) {
        return LawdRequest.builder()
                .lawdCd(fieldSet.readString(LawdType.LAWD_CD.getName()))
                .lawdDong(fieldSet.readString(LawdType.LAWD_DONG.getName()))
                // 존재 라는 문자열이 있다면 true를 넣어주고 아니면 false를 넣는다
                .exist(fieldSet.readBoolean(LawdType.EXIST.getName(), LawdType.EXIST_TRUE.getName()))
                .build();
    }
}
