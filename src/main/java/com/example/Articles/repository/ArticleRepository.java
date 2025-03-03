package com.example.Articles.repository;

import com.example.Articles.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT a FROM Article a JOIN a.tags t WHERE t.name IN :tags")
    List<Article> findByTags(@Param("tags") List<String> tags);

}
