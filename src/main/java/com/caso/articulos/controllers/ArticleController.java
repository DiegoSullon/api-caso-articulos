package com.caso.articulos.controllers;

import java.util.List;

import com.caso.articulos.models.Articulo;
import com.caso.articulos.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("list")
    public ResponseEntity<List<Articulo>> listArticles(){
        List<Articulo> articulos = articleService.listArticles();
        return new ResponseEntity<List<Articulo>>(articulos, HttpStatus.OK);
    }
}
