package com.example.board.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

// 공통 필드를 하나의 Entity로 만들어서 각 Entity에 적용
// 생성일자, 수정일자, 로그인사용자
// 하위 Entity에 상속용 Entity
@MappedSuperclass
// 감사기능(이벤트) 적용
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter                                     // 각 변수의 get메소드와 set메소드를 자동생성
public abstract class BaseEntity {

    @Column(name = "regdate", nullable = false, updatable = false)
    @CreatedDate                                    // 레코드 생성시 적용
    private LocalDateTime regdate;                  // 서버의 날짜와 시간

    @Column(name = "modDate")
    @LastModifiedDate                               // 마지막 수정시간에 적용
    private LocalDateTime modDate;                  // 서버의 수정 날짜와 시간

}
