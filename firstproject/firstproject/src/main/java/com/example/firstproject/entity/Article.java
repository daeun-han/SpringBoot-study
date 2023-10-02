package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // Article() 생성자를 대체하는 어노테이션
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString // toString() 메서드를 대체하는 어노테이션
@Entity
public class Article {
    
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue // 대푯값을 자동으로 생성 (숫자가 자동으로 매겨짐)
    private Long id;

    @Column // title 필드 선언, DB 데이블의 title과 연결됨
    private String title;

    @Column
    private String content;
}
