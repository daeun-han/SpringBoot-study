package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    // 반환형이 ResponseEntity<List<CommentDto>>인 이유: DB에서 조회한 댓글 엔티티 목록은 List<Comment>이지만, 엔티티를 DTO로 변환하면 List<CommentDto>가 되기 때문에 + 응답코드를 같이 보내기 위해 ResponseEntity 클래스를 활용함
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId); // service의 메서드와 컨트롤러 메서드의 이름을 일부러 같게 지음
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) { // HTTP 요청의 BODY로부터 JSON 데이터를 받아오므로 / @RequestBody는 HTTP 요청 본문에 실린 내용(JSON, XML, YAML)을 자바 객체로 변환해준다.

        // 컨트롤러는 서비스에 댓글 새엇ㅇ 작업을 위임하고, 단지 결과만 받아서 클라이언트에 응답한다.
        // 서비스에 위임
        CommentDto createdDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 3. 댓글 수정
    // 4. 댓글 삭제
}