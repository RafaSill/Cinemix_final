package com.PI.cinemix.controller;

import com.PI.cinemix.entity.AlugarFilmesEntity;
import com.PI.cinemix.entity.ComprarFilmesEntity;
import com.PI.cinemix.service.AlugarFilmesService;
import com.PI.cinemix.service.ComprarFilmesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico-compras")
public class PagamentoRestController {
    
    @Autowired
    ComprarFilmesService comprarFilmesService;
    
    @Autowired
    AlugarFilmesService alugarFilmesService;
    
    @GetMapping("/compras/{id}")
    public ResponseEntity<List> getAllCompras(@PathVariable Long id){
        List<ComprarFilmesEntity> compra = comprarFilmesService.listaDeCompras(id);
        
        return new ResponseEntity<>(compra, HttpStatus.OK);
    }
    
    @GetMapping("/aluguel/{id}")
    public ResponseEntity<List> getAllAlugueis(@PathVariable Long id){
        List<AlugarFilmesEntity> aluguel = alugarFilmesService.listaDeAluguel(id);
        
        return new ResponseEntity<>(aluguel, HttpStatus.OK);
    }
}
