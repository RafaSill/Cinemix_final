package com.PI.cinemix.entity;

import jakarta.persistence.Column;
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
@Table(name = "comprarFilmes")
public class ComprarFilmesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "filmes_id")
    private FilmesEntity filmes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity users;

    @ManyToOne
    @JoinColumn(name = "tipoPagamento_id")
    private TipoPagamentoEntity tipoPagamento;
    
    private LocalDate dataCompra;
    private double valor;
}
