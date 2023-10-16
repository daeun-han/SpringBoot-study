package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // Article() 생성자를 대체하는 어노테이션
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString // toString() 메서드를 대체하는 어노테이션
@Entity
@Getter // getter는 외부에서 객체의 데이터를 읽을 때 사용한다. (ex. controller)
public class Article {

    @Id // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 대푯값을 자동으로 생성 (숫자가 자동으로 매겨짐) -> DB가 id 자동 생성
    private Long id;

    @Column // title 필드 선언, DB 데이블의 title과 연결됨
    private String title;

    @Column
    private String content;

    public void patch(Article article) { // 수정할 내용이 있는 경우에만 동작 -> 덕분에 일부분만 수정 가능
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }
}
