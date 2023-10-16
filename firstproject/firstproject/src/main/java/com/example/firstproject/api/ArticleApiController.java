package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() { // index 메서드 정의
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) { // show 메서드로 수정, url의 id를 매개변수로 받아 오기
        return articleRepository.findById(id).orElse(null);
    }

    @PostMapping ("/api/articles")
    public Article creat(@RequestBody ArticleForm dto) { // 데이터를 dto 매개변수로 받아 옴. -> @RequestBody 어노테이션을 통해 json 데이터를 받아오게 됨
        Article article = dto.toEntity(); // 받아 온 dto는 DB에서 활용하도록 엔티티로 변환해 변수에 넣음
        return articleRepository.save(article); // repository를 통해 DB에 저장한 후, 반환
    }

    //POST
    //PATCH
    //DELETE
}
