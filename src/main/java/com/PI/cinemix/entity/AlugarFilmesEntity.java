package com.PI.cinemix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "alugarFilmes")
public class AlugarFilmesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity users;
    
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private FilmesEntity filmes;
    
    @ManyToOne
    @JoinColumn(name = "tipoPagamento_id")
    private TipoPagamentoEntity tipoPagamento;
    
    private LocalDate dataInicio, dataDevolucao;
    private double valor;
    private String status;
}
