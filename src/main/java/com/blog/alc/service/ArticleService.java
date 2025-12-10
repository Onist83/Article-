package com.blog.alc.service;

import com.blog.alc.model.Article;
import com.blog.alc.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private List<Article> listArticles = new ArrayList<>();
    private ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
        repository.save(new Article( "113 raisons d’espérer", "Marie Colot", "La trajectoire de Noé, 14 ans, grand « éco-anxieux », depuis la panique qui tétanise, vers l’apaisement et l’action."));
        repository.save(new Article( "Fight !", "Jean Tevelis", "Une histoire poignante sur ce qu’on fait de la violence qu’on reçoit et de celle qu'on a en soi !"));
        repository.save(new Article( "Matin brun", "Franck Pavloff", "Le récit à la troisième personne, de l’extérieur, manipule le personnage et la vigilance finale de Jules ne semble pas très assurée. Un album riche en couleurs et en réflexions."));
        repository.save(new Article( "Indignez-vous !", "Stéphane Hessel", "Indginez-vous! La violente espérance de Stéphane Hessel revient sur l'histoire de l'ouvrage et sur celle de son auteur avec de nombreux détails, sans être difficile à lire, grâce aux talents combinés des auteurs."));
        repository.save(new Article( "Regarde les lumières, mon amour", "Annie Ernaux", "Dans la collection «Raconter la vie», aux éditions du Seuil, Annie Ernaux publie Regarde les lumières mon amour, journal de ses visites pendant un an à l'hypermarché du centre commercial les Trois Fontaines, dans la ville nouvelle que filma Eric Rohmer, Cergy-Pontoise."));
    }


    public List<Article> getListArticles() {
        return this.repository.findAll();
    }

    public Article getArticleById(Long id) throws IllegalAccessException {
        return repository.findById(id)
                .orElseThrow(
                        () -> new IllegalAccessException("Article with id " + id + " not found")
                );
    }

    public void createANewArticle(Article article) {
        repository.save(article);
    }

    public void updateArticle(Article update) {
        try {
            getArticleById(update.getId());
            repository.save(update);
        } catch (IllegalAccessException e) {
            System.out.println("Article with id " + update.getId() + " not found");
            throw new RuntimeException(e);
        }
    }

    public List<Article> getArticlesByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    public void addArticle(Article article) {
        repository.save(article);
    }

    public void modifierArticle(Article modifierArticle) {
        try {
            getArticleById(modifierArticle.getId());
        } catch (IllegalAccessException e) {
            System.out.println("Article with id " + modifierArticle.getId() + " not found");
            throw new RuntimeException(e);
        }
        repository.save(modifierArticle);
    }

    public void deleteArticle(Long id) {
        repository.deleteById(id);
    }


}
