package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
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

    public List<Article> createArticles(List<ArticleForm> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream() // dtos를 stream화 하기, dto의 묶음인 dtos를 엔티티의 묶음으로 만들기 위해 스트림 문법 사용
                .map(dto -> dto.toEntity()) // map()으로 dto가 하나하나 올 때마다 dto.toEntity()를 수행해 매핑
                .collect(Collectors.toList()); // 매핑한 것을 리스트로 묶기, 최종 결과를 articleList에 저장
        // 2. 엔티티 묶음을 DB에 저장하기
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L) // id가 -1인 데이터 찾기
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!")); // orElseThrow() 메서드는 값이 존재하면 그 값을 반환하고, 없으면ㅔ 전달값으로 보낸 예외 발생(IllegalArgumentException는 전달값이 없거나 유효하지 않은 경우를 뜻함.)
        // 4. 결과 값 반환하기
        return articleList;
    }
}
