package com.stack.puzzle.domain.house.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lawd")
@DynamicUpdate // 변경사항이 있는 것만 UPDATE 해줘
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Lawd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lawdId;

    @Column(nullable = false)
    private String lawdCd;

    @Column(nullable = false)
    private String lawdDong;

    @Column(nullable = false)
    private Boolean exist;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public void setLawd(String lawdCd, String lawdDong, Boolean exist) {
        this.lawdCd = lawdCd;
        this.lawdDong = lawdDong;
        this.exist = exist;
    }
}
