package com.PI.cinemix.service;

import com.PI.cinemix.entity.FilmesEntity;
import com.PI.cinemix.repository.FilmesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmesService {
    @Autowired
    FilmesRepository filmesRepository;
    
    //POST
    public FilmesEntity adicionarFilmes(FilmesEntity filme){
        filme.setId(null);
        filmesRepository.save(filme);
        return filme;
    }
    
    //GET
    public List<FilmesEntity> listarTodosFilmes(){
        return filmesRepository.findAll();
    }
    
    //GET
    public FilmesEntity getFilmeById(Long id){
        return filmesRepository.findById(id).orElseThrow(null);
    }
    
    //GET
    public List<FilmesEntity> buscarPorGenero(String genero) {
        return filmesRepository.findByGeneroContainingIgnoreCase(genero);
    }
    
    //PUT
    public FilmesEntity atualizarFilme(Long filmeId, FilmesEntity filmeRequest){
        FilmesEntity filme = getFilmeById(filmeId);
        
        filme.setTitulo(filmeRequest.getTitulo());
        filme.setGenero(filmeRequest.getGenero());
        filme.setFaixaEtaria(filmeRequest.getFaixaEtaria());
        filme.setLancamento(filmeRequest.getLancamento());
        filme.setDuracaoEmMin(filmeRequest.getDuracaoEmMin());
        
        return filmesRepository.save(filme);
    }
    
    //DELETE
    public void deletarFilme(Long id){
        FilmesEntity filme = getFilmeById(id);
        
        filmesRepository.deleteById(filme.getId());
    }
}
