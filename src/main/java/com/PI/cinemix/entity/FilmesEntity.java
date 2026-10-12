package com.PI.cinemix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "filmes")
public class FilmesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "genero")
    private String genero;

    @Column(name = "faixa_etaria")
    private String faixaEtaria;

    @Column(name = "lancamento")
    private LocalDate lancamento;

    @Column(name = "duracao")
    private int duracaoEmMin;

    @NotNull(message = "Informe o preço de aluguel.")
    @DecimalMin(value = "0.01", message = "O preço de aluguel deve ser maior que zero.")
    @Column(nullable = false)
    private Double precoAluguel;

    @NotNull(message = "Informe o preço de compra.")
    @DecimalMin(value = "0.01", message = "O preço de compra deve ser maior que zero.")
    @Column(nullable = false)
    private Double precoCompra;

}
