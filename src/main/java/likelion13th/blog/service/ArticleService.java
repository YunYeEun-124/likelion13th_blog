package likelion13th.blog.service;

import likelion13th.blog.domain.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArticleService {
    private final List<Article> articleDB = new ArrayList<>(); //데이터를 저장할 임시DB
    private  Long nextId = 1L; //고유번호 업데이트

    //게시글 생성
    public Article addArticle(Article article){
        //에러 처리
        if(article.getAuthor() == null //작성자가 비어있거나,
                || article.getContent() == null //내용이 비어있거나,
                || article.getTitle() == null //제목이 비어있거나
                || article.getPassword() == null ){ //비번이 비어있으면 에러처리
            throw new IllegalArgumentException("제목,내용,작성자,비밀번호는 필수 입력 항목입니다.");
        }

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
        //반환
        return newArticle;

    }

    //게시글 전체 조회
    public List<Article> findAll(){
        //임시 DB 반환
        return articleDB;
    }

//    //게시글 1개 조회
    public Article findById(Long id){

        //반복문 이용하여 일치하는 게시글 찾기
        for(Article article: articleDB){
            if(article.getId().equals(id)) {
                return article;
            }
        }
        //해당 id의 게시글이 존재하지 않는 경우
        throw new NoSuchElementException(id+"번 게시글을 찾을 수 없습니다.");
    }

}
