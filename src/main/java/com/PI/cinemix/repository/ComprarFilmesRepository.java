package com.PI.cinemix.repository;

import com.PI.cinemix.entity.ComprarFilmesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprarFilmesRepository extends JpaRepository<ComprarFilmesEntity, Long>{
    List<ComprarFilmesEntity> findByUsers_IdOrderByDataCompraDesc(Long usuarioId);
}
