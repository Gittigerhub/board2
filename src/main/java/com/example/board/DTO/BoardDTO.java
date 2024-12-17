package com.example.board.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDTO {
    // Entity에 있는 변수들(사용하는)만 복사
    // 1. 공통 삽입, 수정, 목록, 상세보기 전부모아 작업 가능
    // 2. DTO로 삽입, 수정, 목록, 등 개별적으로 작업 가능

    private Integer id;

    private String subject;

    private String content;

    private String author;

    private LocalDateTime regdate;

    private LocalDateTime modDate;

}
