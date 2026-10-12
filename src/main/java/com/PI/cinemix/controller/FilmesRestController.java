package com.PI.cinemix.controller;

import com.PI.cinemix.entity.FilmesEntity;
import com.PI.cinemix.service.FilmesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogo")
public class FilmesRestController {
    @Autowired
    FilmesService filmesService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllFilmes(){
        List<FilmesEntity> filmes = filmesService.listarTodosFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FilmesEntity> getFilmeById(@PathVariable Long id){
        FilmesEntity filme = filmesService.getFilmeById(id);
        
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<FilmesEntity> addFilme(@RequestBody FilmesEntity filmes){
        FilmesEntity novoFilme = filmesService.adicionarFilmes(filmes);
        
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmesEntity> atualizarFilme(@PathVariable Long id, @RequestBody FilmesEntity filme){
        var filmeAtualizado = filmesService.atualizarFilme(id, filme);
        
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFilme(@PathVariable Long id){
        filmesService.deletarFilme(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
