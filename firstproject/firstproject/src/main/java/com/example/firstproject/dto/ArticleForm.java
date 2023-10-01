package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {

    // 입력 폼에서 전송 받을 필드
    private String title;
    private String content;

    // 생성자
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 데이터를 잘 받았는지 확인하는 toString() 메서드
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content); // 전달값은 생성자 입력 양식에 맞게 작성후, 엔티티로 반환
    }
}
