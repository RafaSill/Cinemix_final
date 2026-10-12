package com.PI.cinemix.service;

import com.PI.cinemix.entity.AlugarFilmesEntity;
import com.PI.cinemix.repository.AlugarFilmesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlugarFilmesService {
    
    @Autowired
    AlugarFilmesRepository alugarFilmesRepository;
    
    public AlugarFilmesEntity salvarAlugar(AlugarFilmesEntity aluguel){
        return alugarFilmesRepository.save(aluguel);
    }
    
    public List<AlugarFilmesEntity> listaDeAluguel(Long usuarioId){
        return alugarFilmesRepository.
                findByUsers_IdOrderByDataInicioDesc(usuarioId);
    }
}
