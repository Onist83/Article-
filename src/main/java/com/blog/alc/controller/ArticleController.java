package com.blog.alc.controller;

import com.blog.alc.model.Article;
import com.blog.alc.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("listArticles", articleService.getListArticles());
        return "index";
    }
//    public List<Article> getAllArticles() {
//        return articleService.getListArticles();
//    }

    @GetMapping("/nouveau") //createANewArticle
    public String getcreateANewArticle(Model model) {
        model.addAttribute("article", new Article());
        return "form";
    }
//    public void createANewArticle(
//            @RequestBody Article article
//    ) {
//        articleService.createANewArticle(article);&a
//    }

    @PostMapping("add-article")
    public String addArticle(@ModelAttribute Article a, Model model) {
        articleService.addArticle(a);
        return "index";
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
    public String getModifierArticle(Model model) {
        model.addAttribute("modifierArticle", articleService.getArticleById(id));
        return "form";
    }


    @PostMapping("/{id}/modifier")
    public String modifierArticle(@ModelAttribute Article a, Model model) {
        articleService.modifierArticle(a);
        return "redirect:/";
    }

    @GetMapping("{id}/supprimer")
    public String deleteArticle(
            @PathVariable Long id
    ) {
        articleService.deleteArticle(id);
        return "redirect:/";
    }
}
