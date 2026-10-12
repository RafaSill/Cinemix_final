package com.PI.cinemix.repository;

import com.PI.cinemix.entity.AlugarFilmesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlugarFilmesRepository extends JpaRepository<AlugarFilmesEntity, Long>{
    List<AlugarFilmesEntity> findByUsers_IdOrderByDataInicioDesc(Long usuarioId);
}
