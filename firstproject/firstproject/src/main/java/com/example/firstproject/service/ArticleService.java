package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j //로그 찍게하는 어노테이션
@Service // 서비스 객체 생성
public class ArticleService {
    @Autowired // 생성 객체를 가져와 연결
    private ArticleRepository articleRepository; // 게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity(); // 받아 온 dto는 DB에서 활용하도록 엔티티로 변환해 변수에 넣음

        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article); // repository를 통해 DB에 저장
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔티티 변환하기 (수정용 엔티티 생성)
        Article article = dto.toEntity(); // dto를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());

        // 2. (DB에서) 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null); // 레포지토리의 메서드를 통해 DB에서 엔티티를 가져오되, 없으면 null을 반환

        // 3. 잘못된 요청 처리하기 (대상 엔티티가 없거나, 수정하려는 id가 잘못됐을 경우)
        if (target == null || id != article.getId()) { // 잘못된 요청인지 판별 (대상 엔티티가 없거나, 수정 요청 id와 본문 id가 다를 경우)
            // 400, 잘못된 요청 응답
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        // 4. (대상 엔티티가 있으면) 업데이트 및 정상 응답(200)하기
        target.patch(article); // 기존 데이터에 새 데이터 붙이기(보강하는 코드. null이 되지 않도록)
        Article updated = articleRepository.save(target); // article 엔티티 DB에 저장 -> 수정 내용 DB에 최종 저장
        return updated; // 응답은 컨트롤러가 하므로 여기서는 수정 데이터만 반환
    }

    public Article delete(Long id) {
        // 1. 대상 찾기 (DB에서 대상 엔티티가 있는지 조회)
        Article target = articleRepository.findById(id).orElse(null); // 레포지토리의 메서드를 통해 DB에서 엔티티를 가져오되, 없으면 null을 반환

        // 2. (대상 엔티티가 없어서)잘못된 요청 처리하기
        if (target == null) { // 잘못된 요청인지 판별 (대상 엔티티가 없을 때)
            return null; // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        // 3. 대상 삭제하기(대상 엔티티가 있으면 삭제한 후, 정상 응답(200) 반환하기)
        articleRepository.delete(target);
        return target; // DB에서 삭제한 대상을 컨트롤러에 반환
    }
}
