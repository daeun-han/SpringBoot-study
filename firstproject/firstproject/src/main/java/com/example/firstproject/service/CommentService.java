package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // 게시글 리파지터리가 있어야 댓글을 생성할 때 대상 게시글의 존재 여부를 파악할 수 있음.

    public List<CommentDto> comments(Long articleId) {

//        // 1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId); // List<Comment>타입인 이유: 한 게시글에 여러 댓글이 달릴 수 있기 때문 -> Comment를 리스트로 받아오기 위함
//
//        // 2. 엔티티 -> DTO 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>(); //dtos는 레퍼런스 변수로 댓글 DTO를 관리하는 ArrayList를 가리킨다.
//        for (int i = 0; i < comments.size(); i++) { // 조회한 댓글 엔티티 수만큼 반복하기
//            Comment c = comments.get(i); // 하나씩 가져오기
//            // 엔티티를 DTO로 변환
//            CommentDto dto = CommentDto.createCommentDto(c); // createCommentDto는 댓글 엔티티를 입력받아 CommentDto 객체를 반환하는 메서드이다.
//            dtos.add(dto); // 변환한 DTO를 dtos 리스트에 삽입
//        }

        // 서비스 코드의 for 문을 스트림 문법으로 개선
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId) // 댓글 엔티티 목록 조회
                .stream() // 댓글 엔티티 목록을 스트림으로 변화
                .map(comment -> CommentDto.createCommentDto(comment)) // 스트림의 각 요소(comment)를 꺼내 CommentDto.createCommentDto(comment)를 수행한 결과로 매핑 = 엔티티를 DTO로 매핑
                .collect(Collectors.toList()); // 스트림 데이터를 리스트 자료형으로 변환
    }
}
