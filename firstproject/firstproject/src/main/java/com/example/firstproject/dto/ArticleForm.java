package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든 필드들에 대해 생성자를 자동으로 생성하는 어노테이션 (lombok)
@ToString // 마찬가지로 tostring에 관한 메소드 담당 어노테이션
public class ArticleForm {

    private Long id; // 수정 폼에 <input> 태그로 id를 추가했으므로(hidden 타입) DTO를 정의하는 ArticleForm에도 id를 추가함

    // 입력 폼에서 전송 받을 필드
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content); // 전달값은 생성자 입력 양식에 맞게 작성후, 엔티티로 반환
    }
}
