package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) { // DTO -> Entity
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
        // BoardRepository 클래스 내에 없지만 상속받은 save 함수 호출
    }

    public List<BoardDTO> findALL() { // Entity -> DTO
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional
    // 커스텀 쿼리 중, 데이터 변경(CUD)이 일어나는 로직에서 필수인 어노테이션
    // 혹은, 여러 작업을 하나로 묶을 때 사용
    // 커스텀 쿼리가 아닌 JpaRepository를 사용할 경우, 이 어노테이션이 포함되어 있어서 안 써도 됨.
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) { // 값이 있으면 반환
            BoardEntity boardEntity = optionalBoardEntity.get(); // 내부 데이터 반환
            return BoardDTO.toBoardDTO(boardEntity);
            //findById() -> Optional 형태 반환 -> get() -> 내부 데이터 BoardEntity 형태 반환 -> BoardDTO 변환 -> 리턴
        }
        else return null;
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    // 트랜잭션 어노테이션이 붙은 메서드는, 불러온 Entity 객체를 영속성 컨텍스트에 등록하고 감시를 시작한다.
    // 반약 트랜잭션이 종료됐을 때 객체 데이터의 변화가 있을 경우, 자동으로 UPDATE 쿼리를 DB에 전송한다.
    public void update(BoardDTO boardDTO) {
        BoardEntity boardEntity = boardRepository.findById(boardDTO.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        // Optional<> 로 반환하는 방식말고 다른 방식(예외 처리도 한번에 하는 코드)
        if (boardEntity.getBoardPass().equals(boardDTO.getUpdatePass())) {
            boardEntity.update(boardDTO);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
