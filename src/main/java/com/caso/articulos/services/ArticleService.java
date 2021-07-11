package com.caso.articulos.services;

import java.util.List;

import com.caso.articulos.models.Articulo;
import com.caso.articulos.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Articulo> listArticles(){
        return articleRepository.findAll();
    }
}
