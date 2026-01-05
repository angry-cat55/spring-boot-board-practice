package com.example.demo.dto;

import com.example.demo.entity.BoardEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter // getBoardWriter() 등 생성
@Setter // setBoardWriter() 등 생성
@ToString // 객체의 주소값 대신 내용물을 출력하도록 하는 메소드 생성
public class BoardDTO {
    private Long    id; // PK 아이디
    private String  boardWriter; // 작성자
    private String  boardPass; // 비밀번호
    private String  updatePass; // 인증용 수정 페이지의 비밀번호
    private String  boardTitle; // 제목
    private String  boardContents; // 내용
    private int     boardHits; // 조회수
    private String  boardCreatedAt; // 작성일자

    private String dateFormat(LocalDateTime date) {
        if (date == null)
            return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedAt(boardDTO.dateFormat(boardEntity.getCreatedAt()));
        return boardDTO;
    }
}
