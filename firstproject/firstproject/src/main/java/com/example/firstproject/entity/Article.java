package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Article {
    
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue // 대푯값을 자동으로 생성 (숫자가 자동으로 매겨짐)
    private Long id;

    @Column // title 필드 선언, DB 데이블의 title과 연결됨
    private String title;

    @Column
    private String content;

    // Article 생성자 추가
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // toString() 메서드 추가
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
