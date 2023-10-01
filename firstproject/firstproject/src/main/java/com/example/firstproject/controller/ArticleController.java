package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해 놓은 리퍼지토리 객체 주입(DI) -> 구현체 필요 X
    private ArticleRepository articleRepository; // 객체 선언
    
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // 폼에서 전송한 데이터를 메서드의 매개변수로 받아 옴 (폼 데이터를 DTO로 받기), ArticleForm 타입의 form 객체를 매개변수로 선언
        System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인, 이후 로깅하는 방식으로 바뀔 예정

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity(); // Article 클래스가 엔티티 클래스임.
        System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 확인 출력

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 확인 출력

        return "";
    }
}
