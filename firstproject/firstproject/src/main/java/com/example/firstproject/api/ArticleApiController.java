package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j //로그 찍게하는 어노테이션
@RestController
public class ArticleApiController {

    @Autowired // 생성 객체를 가져와 연결
    private ArticleService articleService; // 서비스 객체 주입

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() { // index 메서드 정의
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) { // show 메서드로 수정, url의 id를 매개변수로 받아 오기
        return articleService.show(id);
    }


    //POST
    @PostMapping ("/api/articles")
    public ResponseEntity<Article> creat(@RequestBody ArticleForm dto) { // 데이터를 dto 매개변수로 받아 옴. -> @RequestBody 어노테이션을 통해 json 데이터를 받아오게 됨
        Article created = articleService.create(dto); // 받아 온 dto는 DB에서 활용하도록 엔티티로 변환해 변수에 넣음, 서비스로 게시글 생성

        //삼항 연산자 사용, 생성하면 정상, 실패하면 오류 응답
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) { // update 메서드 정의, 반환형 ResponseEntity로 수정
        Article updated = articleService.update(id, dto); // 서비스 통해 게시글 수정

        //삼항 연산자 사용, 수정되면 정상, 실패하면 오류 응답
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //본문이 없어서 빌드만 함.
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
            Article deleted = articleService.delete(id); // 서비스 통해 게시글 수정

            //삼항 연산자 사용, 삭제되면 정상, 실패하면 오류 응답
            return (deleted != null) ?
                    ResponseEntity.status(HttpStatus.NO_CONTENT).build() : // 삭제됐기에 본문이 없음
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //본문이 없어서 빌드만 함.
    }

    @PostMapping("/api/transaction-test") // 여러 게시글 생성 요청 접수
    // 서버에서 응답할 때, 데이터 생성 결과뿐만 아니라 상태 코드도 함께 보내므로 메서드의 반환형은 ResponseEntity<List<Article>>로 설정.
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) { // 매개변수로 ArticleForm 데이터를 List로 묶은 dtos를 선언, @RequestBody는 REST API 방식으로 POST 요청을 받고있기에 붙임.
        List<Article> createdList = articleService.createArticles(dtos); // 서비스 호출
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
