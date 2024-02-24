package com.stack.puzzle.domain.house.service;

import com.stack.puzzle.domain.house.dto.LawdRequest;
import com.stack.puzzle.domain.house.entity.Lawd;
import com.stack.puzzle.domain.house.repository.LawdRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LawdService {
    /**
     * 생성자 주입 방식 사용 이유
     * 1. 순환 참조 방지 (bean 생성을 나중에 함)
     * 2. 테스트 용이 (Mock을 사용하지 않아도 됨)
     * 3. 불변성 보장 (final)
     */
    private final LawdRepository lawdRepository;


    /**
     * 새로 업데이트 된 동이 있다면 수정 한다
     * @param lawdRequest
     */
    @Transactional  // 일관성있는 DB 상태 유지
    public void upsert(LawdRequest lawdRequest) {
        // 영속화, id 이용해서 값 찾기, 이걸 해줘야 Dirty Checking(상태 변경 검사)이 탐지하고 UPDATE 해줌
        Lawd lawd = lawdRepository.findByLawdCd(lawdRequest.getLawdCd())
                .orElseGet(lawdRequest::toEntity);

        // LawdRequest에서 받은 값으로 Lawd 엔티티를 업데이트합니다.
        lawd.setLawd(lawdRequest.getLawdCd(), lawdRequest.getLawdDong(), lawdRequest.getExist());

        // 변경사항을 저장합니다. 만약 Lawd 엔티티가 새로 생성되었다면 새로운 엔티티가 저장되고,
        // Lawd 엔티티가 이미 존재한다면 해당 엔티티의 변경사항이 데이터베이스에 반영됩니다.
        lawdRepository.save(lawd);
    }
}
