package com.caso.articulos.controllers;

import java.util.ArrayList;
import java.util.List;

import com.caso.articulos.dto.ArticuloDto;
import com.caso.articulos.dto.Message;
import com.caso.articulos.models.Articulo;
import com.caso.articulos.services.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
    public ResponseEntity<List<Articulo>> listArticles(){
        List<Articulo> articulos = articleService.listArticles();
        return new ResponseEntity<List<Articulo>>(articulos, HttpStatus.OK);
    }
    @GetMapping("/sort")
    public ResponseEntity<List<Articulo>> listArticlesSort(   
        @RequestParam(defaultValue = "nombre") String order,
        @RequestParam(defaultValue = "true") boolean asc
    ){
        List<Articulo> articulos = new ArrayList<>();
        if(asc){
            articulos = articleService.listArticles(Sort.by(Sort.Direction.ASC, order));
        }else{
            articulos = articleService.listArticles(Sort.by(Sort.Direction.DESC, order));
        }
        
        return new ResponseEntity<List<Articulo>>(articulos, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Message> create(@RequestBody ArticuloDto articuloDto) {
        if (articuloDto.getNombre().length()<4)
            return new ResponseEntity<Message>(new Message("El nombre debe tener al menos 4 caracteres"), HttpStatus.BAD_REQUEST);
        if (articuloDto.getCodigo().length()<4)
            return new ResponseEntity<Message>(new Message("El codigo debe tener al menos 4 caracteres"), HttpStatus.BAD_REQUEST);
        if (articleService.existsByCodigo(articuloDto.getCodigo()))
            return new ResponseEntity<Message>(new Message("Codigo ya registrado anteriormente"), HttpStatus.BAD_REQUEST);
        if (articuloDto.getPrecioVenta()<1)
            return new ResponseEntity<Message>(new Message("El precio debe de ser mayor o igual a 1"), HttpStatus.BAD_REQUEST);
        Articulo articulo = new Articulo();
        articulo.setCodigo(articuloDto.getCodigo());
        articulo.setNombre(articuloDto.getNombre());
        articulo.setPrecioVenta(articuloDto.getPrecioVenta());
        articulo.setStock(articuloDto.getStock());
        articulo.setDescripcion(articuloDto.getDescripcion());
        articulo.setImagen(articuloDto.getImagen());
        articulo.setEstado(articuloDto.getEstado());
        articleService.save(articulo);
        return new ResponseEntity<Message>(new Message("Article saved"),HttpStatus.OK);
    }
}
