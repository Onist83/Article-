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

    @GetMapping("/form") //createANewArticle
    public String getcreateANewArticle(Model model) {
        model.addAttribute("article", new Article());
        return "form";
    }

    @GetMapping("/detail/{id}")
    public String getDetailArticle(Model model, @PathVariable Long id) throws IllegalAccessException {
        model.addAttribute("article", articleService.getArticleById(id));
        return "detail";
    }

    @GetMapping("/{id}/modifier")
    public String getModifierArticle(Model model, @PathVariable Long id) throws IllegalAccessException {
        model.addAttribute("article", articleService.getArticleById(id));
        return "form";
    }

    @GetMapping("{id}/supprimer")
    public String deleteArticle(
            @PathVariable Long id
    ) {
        articleService.deleteArticle(id);
        return "redirect:/";
    }

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

    @GetMapping("/rechercher")
    public String getRechercherArticle(Model model, String author) throws IllegalAccessException {
        model.addAttribute("listArticles", articleService.getArticlesByAuthor(author));
        return "index";
    }

    // creer un form pour rechercher l'author apres validation
    // renvoyer la page d'acceuil avec seulement la recherche
    // idem plus ou moins que article new avec renvoie form vide

    @PostMapping("add-article")
    public String addArticle(@ModelAttribute Article a, Model model) {
        articleService.addArticle(a);
        return "redirect:/";
    }

    @PostMapping("/{id}/modifier")
    public String modifierArticle(@ModelAttribute Article a, Model model) {
        articleService.modifierArticle(a);
        return "redirect:/";
    }
}
