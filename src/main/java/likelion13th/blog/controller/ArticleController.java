package likelion13th.blog.controller;

import likelion13th.blog.domain.Article;
import likelion13th.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/*
* ArticleController -> ArticleService
* */

@RestController
@RequestMapping("/articles")
public class ArticleController {

    // ArticleService 타입의 필드를 선언
    private final ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService; // 주입받은 값을 필드에 할당
    }


    //게시글 생성
    @PostMapping()
    public ResponseEntity<Article> createArticle(@RequestBody Article article){

        Article newArticle = articleService.addArticle(article);


        //저장한 객체 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(newArticle);

    }

    //게시글 전체 조회
    @GetMapping()
    public ResponseEntity<List<Article>> getArticles(){

        List<Article> articles = articleService.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articles);
    }

//    //게시글 1개 조회
    // GET : /articles/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id){


        Article article = articleService.findById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(article);
    }


}
