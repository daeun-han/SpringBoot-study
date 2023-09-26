package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼에서 전송한 데이터를 메서드의 매개변수로 받아 옴 (폼 데이터를 DTO로 받기), ArticleForm 타입의 form 객체를 매개변수로 선언
        System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인, 이후 로깅하는 방식으로 바뀔 예정
        return "";
    }
}
