package com.example.demo.repository;

import com.example.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // save 기능 호출 시 상속받은 JpaRepository에서의 save 함수를 호출

    @Modifying
    // IUD(INSERT, UPDATE, DELETE) 쿼리 실행 시 필수 어노테이션 (데이터 변화X(조회)일때는 필요X)
    // 데이터 변경이 일어나는 로직이기 때문에, Service에서 @Transactional 어노테이션 필수
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
    // 실행할 커스텀 쿼리문
    void updateHits(@Param("id") Long id);
}