package com.blog.controller;

import com.blog.model.Article;
import com.blog.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/index")
    public List getIndex(Model model) {
        model.
        return "index";
    }
//    public List<Article> getAllArticles() {
//        return articleService.getListArticles();
//    }

    @GetMapping("/nouveau") //createANewArticle
    public String getcreateANewArticle(Model model) {
        model.addAttribute("article", new Article());
        return "nouveau";
    }
//    public void createANewArticle(
//            @RequestBody Article article
//    ) {
//        articleService.createANewArticle(article);&a
//    }

    @PostMapping("add-article")
    public String addArticle(@ModelAttribute Article a, Model model) {
        articleService.addArticle(a);
        return "acceuil";
    }
//            @RequestBody Article article
//    ) {
//        articleService.addArticle(article);
//    }

    @GetMapping("/{id}")
    public Article getArticleById(
            @PathVariable Long id
    ) {
        try {
            return articleService.getArticleById(id);
        } catch (IllegalAccessException e) {
            System.out.println("Article with id " + id + " not found");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/modifier")


    @PostMapping("/{id}/modifier")
    public void modifierArticle(
            @PathVariable Long id,
            @RequestBody Article modifierArticle
    ) {
        modifierArticle.setId(id);
        articleService.modifierArticle(modifierArticle);
    }

    @GetMapping("{id}/supprimer")
    public void deleteArticle(
            @PathVariable Long id
    ) {
        articleService.deleteArticle(id);
    }
}
