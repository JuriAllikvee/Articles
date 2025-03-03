package com.example.Articles;

import com.example.Articles.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArticlesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticlesApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            ArticleService articleService = ctx.getBean(ArticleService.class);
            articleService.runConsole();
        };
    }
}
