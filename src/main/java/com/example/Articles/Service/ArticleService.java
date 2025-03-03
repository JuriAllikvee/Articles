package com.example.Articles.service;

import com.example.Articles.entity.Article;
import com.example.Articles.entity.User;
import com.example.Articles.entity.Tag;
import com.example.Articles.repository.ArticleRepository;
import com.example.Articles.repository.UserRepository;
import com.example.Articles.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final Scanner scanner;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.scanner = new Scanner(System.in);
    }

    public void runConsole() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Найти статьи по названию");
            System.out.println("2. Найти статьи по тегам");
            System.out.println("3. Найти пользователя по домену email");
            System.out.println("4. Найти тег по имени");
            System.out.println("5. Выйти");
            System.out.print("Введите номер операции: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> searchByTitle();
                case 2 -> searchByTags();
                case 3 -> searchUsersByEmailDomain();
                case 4 -> searchTagByName();
                case 5 -> {
                    System.out.println("Выход из программы...");
                    return;
                }
                default -> System.out.println("Неверный ввод, попробуйте снова.");
            }
        }
    }

    private void searchByTitle() {
        System.out.print("Введите часть названия статьи: ");
        String title = scanner.nextLine();
        List<Article> articles = articleRepository.findByTitleContainingIgnoreCase(title);
        if (articles.isEmpty()) {
            System.out.println("Статьи с таким названием не найдены.");
        } else {
            articles.forEach(System.out::println);
        }
    }

    private void searchByTags() {
        System.out.print("Введите теги через запятую: ");
        String tagInput = scanner.nextLine();
        List<String> tags = List.of(tagInput.split(",\\s*"));
        List<Article> articles = articleRepository.findByTags(tags);
        if (articles.isEmpty()) {
            System.out.println("Статьи с такими тегами не найдены.");
        } else {
            articles.forEach(System.out::println);
        }
    }

    private void searchUsersByEmailDomain() {
        System.out.print("Введите домен email (например, @gmail.com): ");
        String domain = scanner.nextLine();
        List<User> users = userRepository.findUsersByEmailDomain(domain);
        if (users.isEmpty()) {
            System.out.println("таким доменом email не найдены.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private void searchTagByName() {
        System.out.print("Введите имя тега: ");
        String name = scanner.nextLine();
        Optional<Tag> tag = tagRepository.findByName(name);
        if (tag.isEmpty()) {
            System.out.println("Тег с таким именем не найден.");
        } else {
            System.out.println(tag.get());
        }
    }
}