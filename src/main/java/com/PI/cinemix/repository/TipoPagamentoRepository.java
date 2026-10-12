package com.PI.cinemix.repository;

import com.PI.cinemix.entity.TipoPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TipoPagamentoEntity, Long>{
    
}
