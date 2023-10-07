package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j // 로깅 기능을 위한 어노테이션
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
        log.info(form.toString()); // 로깅 코드 추가
        //        System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인, 이후 로깅하는 방식으로 바뀔 예정

        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity(); // Article 클래스가 엔티티 클래스임.
        log.info(article.toString()); // 로깅 코드 추가
//        System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 확인 출력

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString()); // 로깅 코드 추가
//        System.out.println(saved.toString()); // article이 DB에 잘 저장되는지 확인 출력

        return "redirect:/articles/" + saved.getId(); // id 값을 가져오기 위해 saved 객체 이용, + 연산자 이용해 id에 따라 url 주소가 달라지게 함.
    }

    @GetMapping("/articles/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) { // 매개변수로 id 받아 오기, @PathVariable은 URL 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져오는 어노테이션이다.
        log.info("id = "+id); // id 잘 받았는지 확인하는 로그

//        1. id를 조회해 데이터 가져오기
        // Optional 타입으로 반환 -> 반환형이 Article이 아님
        Article articleEntity = articleRepository.findById(id).orElse(null); // id 값으로 데이터를 찾을 때, id 값이 없으면 null을 반환

//        2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity); // article이라는 이름으로 articleEntity 객체 추가
        
//        3. 뷰 페이지 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); // DB에서 수정할 데이터 가져오기

        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }
}
