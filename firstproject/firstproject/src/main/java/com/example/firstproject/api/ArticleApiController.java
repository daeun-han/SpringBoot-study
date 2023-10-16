package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //로그 찍게하는 어노테이션
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


    //POST
    @PostMapping ("/api/articles")
    public Article creat(@RequestBody ArticleForm dto) { // 데이터를 dto 매개변수로 받아 옴. -> @RequestBody 어노테이션을 통해 json 데이터를 받아오게 됨
        Article article = dto.toEntity(); // 받아 온 dto는 DB에서 활용하도록 엔티티로 변환해 변수에 넣음
        return articleRepository.save(article); // repository를 통해 DB에 저장한 후, 반환
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) { // update 메서드 정의, 반환형 ResponseEntity로 수정

        // 1. DTO -> 엔티티 변환하기 (수정용 엔티티 생성)
        Article article = dto.toEntity(); // dto를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());

        // 2. (DB에서) 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null); // 레포지토리의 메서드를 통해 DB에서 엔티티를 가져오되, 없으면 null을 반환

        // 3. 잘못된 요청 처리하기 (대상 엔티티가 없거나, 수정하려는 id가 잘못됐을 경우)
        if (target == null || id != article.getId()) { // 잘못된 요청인지 판별 (대상 엔티티가 없거나, 수정 요청 id와 본문 id가 다를 경우)
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // ResponseEntity 반환(왜냐하면 Article을 ResponseEntity에 담아서 반환해야만 반환하는 데이터에 상태 코드를 실어 보낼 수 있음.)
        }

        // 4. (대상 엔티티가 있으면) 업데이트 및 정상 응답(200)하기
        target.patch(article); // 기존 데이터에 새 데이터 붙이기(보강하는 코드. null이 되지 않도록)
        Article updated = articleRepository.save(target); // article 엔티티 DB에 저장 -> 수정 내용 DB에 최종 저장
        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상 응답 (상태 코드 200은 HttpStatus.OK이다.)
    }

    //DELETE
}
