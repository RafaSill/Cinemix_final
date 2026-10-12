package com.PI.cinemix.service;

import com.PI.cinemix.entity.ComprarFilmesEntity;
import com.PI.cinemix.repository.ComprarFilmesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprarFilmesService {
    
    @Autowired
    private ComprarFilmesRepository comprarFilmesRepository;
    
    public ComprarFilmesEntity salvarCompra(ComprarFilmesEntity compra){
        return comprarFilmesRepository.save(compra);
    }
    
    public List<ComprarFilmesEntity> listaDeCompras(Long usuarioId){
        return comprarFilmesRepository.
                findByUsers_IdOrderByDataCompraDesc(usuarioId);
    }
}
