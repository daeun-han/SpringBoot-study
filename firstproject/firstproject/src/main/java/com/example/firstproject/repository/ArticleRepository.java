package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> { // 받는 제네릭 요소들 <관리 대상 엔티티의 클래스 타입, 엔티티의 대푯값(id) 타입>
}
