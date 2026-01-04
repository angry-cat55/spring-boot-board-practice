package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // getBoardWriter() 등 생성
@Setter // setBoardWriter() 등 생성
@ToString // 객체의 주소값 대신 내용물을 출력하도록 하는 메소드 생성
public class BoardDTO {
    private Long    id; // PK 아이디
    private String  boardWriter; // 작성자
    private String  boardPass; // 비밀번호
    private String  boardTitle; // 제목
    private String  boardContents; // 내용
    private int     boardHits; // 조회수
    private String  boardCreatedAt; // 작성일자
}
