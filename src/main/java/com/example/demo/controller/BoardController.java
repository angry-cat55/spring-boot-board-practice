package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// 해당 클래스는 웹 요청을 처리하는 컨트롤러임을 명시하는 어노테이션.
// 만약, 화면이 아닌 데이터(JSON 등)만 보낼 경우 @RestController 사용함.
@RequiredArgsConstructor
// 생성자 주입 방식 선언 어노테이션
// 반드시 값이 있어야 하는 변수(final 등)만 골라서 생성사 생성
@RequestMapping("/board")
// 해당 어노테이션을 사용할 경우, 내부의 모든 메서드 앞에 '/board'라는 주소가 자동으로 추가됨.
public class BoardController {
    private final BoardService boardService;
    // 객체 생성 (@RequestArgsConstructor 필요)
    @GetMapping("/save")
    // 'board/save'라는 주소로 GET 방식의 요청이 오면 하단의 saveForm이 호출됨.
    // HTTP 메서드가 GET 방식인 경우에 사용하는 어노테이션 <-> @PostMapping
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    // 'board/save'라는 주소로 POST 방식의 요청이 오면 호출
    public String save(BoardDTO boardDTO) {
        /* 문자열 형태로 전달받은 데이터(boardWriter=홍길동&boardPass=1234&...)를
        컨트롤러의 매개변수 타입에 맞게 데이터를 매핑함.(데이터 바인딩)*/
        // 스프링이 BoardDTO 기본 생성자를 호출 -> 빈 객체 생성 -> 데이터 바인딩 -> 객체 사용.
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "index";
    }
}