package com.caso.articulos.services;

import java.util.List;

import com.caso.articulos.models.Articulo;
import com.caso.articulos.repositories.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Articulo> listArticles(){
        return articleRepository.findAll();
    }
    public List<Articulo> listArticles(Sort arg0){
        return articleRepository.findAll(arg0);
    }
    public void  save(Articulo articulo){
        articleRepository.save(articulo);
    }
    public boolean existsByCodigo(String codigo){
        return articleRepository.existsByCodigo(codigo);
    }
    public boolean existsById(int id){
        return articleRepository.existsById(id);
    }
}
