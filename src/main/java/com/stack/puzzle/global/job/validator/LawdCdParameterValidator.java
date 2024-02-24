package com.stack.puzzle.global.job.validator;

import com.stack.puzzle.domain.house.consts.LawdType;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

public class LawdCdParameterValidator implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        String lawdCd = LawdType.LAWD_CD.getName();

        if (isNotValid(lawdCd)) {
            throw new JobParametersInvalidException(lawdCd + "은 5자리 문자열이어야 합니다.");
        }
    }

    private boolean isNotValid(String lawdCd) {
        return !isValid(lawdCd);
    }

    private boolean isValid(String lawdCd) {
        return StringUtils.hasText(lawdCd) && lawdCd.length() == 5;
    }
}
