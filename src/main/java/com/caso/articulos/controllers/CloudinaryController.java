package com.caso.articulos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

import com.caso.articulos.dto.Message;
import com.caso.articulos.services.CloudinaryService;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CloudinaryController {
    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<Message>(new Message("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        List<String> imagen = new ArrayList<>();
        imagen.add((String) result.get("original_filename"));
        imagen.add((String) result.get("url"));
        System.out.println(imagen.get(1));
        imagen.add((String) result.get("public_id"));

        return new ResponseEntity<Message>(new Message(imagen.get(1) + ":-:" + imagen.get(2)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) throws IOException {
        Map result = cloudinaryService.delete(id);
        System.out.println("Result: " + result);

        return new ResponseEntity<Message>(new Message("imagen eliminada"), HttpStatus.OK);
    }
}