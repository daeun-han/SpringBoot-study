package com.example.firstproject.dto;

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
}
