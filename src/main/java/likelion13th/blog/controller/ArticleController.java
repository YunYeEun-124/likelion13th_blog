package likelion13th.blog.controller;

import likelion13th.blog.domain.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final List<Article> articleDB = new ArrayList<>(); //데이터를 저장할 임시DB
    private  Long nextId = 1L; //고유번호 업데이트

    @PostMapping()
    public ResponseEntity<Article> createArticle(@RequestBody Article article){

        //Article 객체 생성
        Article newArticle = new Article(
                nextId++,
                article.getContent(),
                article.getTitle(),
                article.getAuthor(),
                article.getPassword()
        );

        //DB에 객체 저장
        articleDB.add(newArticle);

        //저장한 객체 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newArticle);

    }

    @GetMapping()
    public ResponseEntity<List<Article>> getArticle(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleDB);
    }
}
