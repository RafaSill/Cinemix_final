package com.PI.cinemix.repository;

import com.PI.cinemix.entity.FilmesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmesRepository extends JpaRepository<FilmesEntity, Long>{
    List<FilmesEntity> findByGeneroContainingIgnoreCase(String genero);
}
