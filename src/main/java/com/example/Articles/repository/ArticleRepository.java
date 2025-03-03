package com.example.Articles.repository;

import com.example.Articles.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticalRepository extends JpaRepository<Article, Long> {
}
