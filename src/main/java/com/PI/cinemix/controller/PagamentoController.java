package com.PI.cinemix.controller;

import com.PI.cinemix.entity.AlugarFilmesEntity;
import com.PI.cinemix.entity.ComprarFilmesEntity;
import com.PI.cinemix.entity.FilmesEntity;
import com.PI.cinemix.entity.TipoPagamentoEntity;
import com.PI.cinemix.entity.UsersEntity;
import com.PI.cinemix.repository.FilmesRepository;
import com.PI.cinemix.repository.TipoPagamentoRepository;
import com.PI.cinemix.repository.UsersRepository;
import com.PI.cinemix.service.AlugarFilmesService;
import com.PI.cinemix.service.ComprarFilmesService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagamentoController {

    @Autowired
    ComprarFilmesService comprarFilmesService;

    @Autowired
    AlugarFilmesService alugarFilmesService;

    @Autowired
    private FilmesRepository filmesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @GetMapping("/historico-compras")
    public String abrirHistorico(HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");

        if (usuarioId == null) {
            return "redirect:/";
        }

        model.addAttribute(
                "compras",
                comprarFilmesService.listaDeCompras(usuarioId)
        );

        model.addAttribute(
                "alugueis",
                alugarFilmesService.listaDeAluguel(usuarioId)
        );

        return "historico-compras";
    }

    @PostMapping("/pagar")
    public String pagar(
            @RequestParam Long filmeId,
            @RequestParam Long tipoPagamentoId,
            @RequestParam String tipoOperacao,
            @RequestParam(required = false)
            @org.springframework.format.annotation.DateTimeFormat(
                    iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE
            ) LocalDate dataInicio,
            @RequestParam(required = false)
            @org.springframework.format.annotation.DateTimeFormat(
                    iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE
            ) LocalDate dataDevolucao,
            HttpSession session) {

        Long usuarioId = (Long) session.getAttribute("usuarioId");

        if (usuarioId == null) {
            return "redirect:/";
        }

        UsersEntity usuario = usersRepository.findById(usuarioId)
                .orElseThrow(()
                        -> new IllegalArgumentException("Usuário não encontrado"));

        FilmesEntity filme = filmesRepository.findById(filmeId)
                .orElseThrow(()
                        -> new IllegalArgumentException("Filme não encontrado"));

        TipoPagamentoEntity pagamento
                = tipoPagamentoRepository.findById(tipoPagamentoId)
                        .orElseThrow(()
                                -> new IllegalArgumentException(
                                "Forma de pagamento não encontrada"));

        if ("COMPRA".equals(tipoOperacao)) {

            ComprarFilmesEntity compra = new ComprarFilmesEntity();

            compra.setUsers(usuario);
            compra.setFilmes(filme);
            compra.setTipoPagamento(pagamento);
            compra.setDataCompra(LocalDate.now());
            compra.setValor(filme.getPrecoCompra());

            comprarFilmesService.salvarCompra(compra);

        } else if ("ALUGUEL".equals(tipoOperacao)) {

            if (dataInicio == null || dataDevolucao == null
                    || dataDevolucao.isBefore(dataInicio)) {
                return "redirect:/escolher-filme";
            }

            AlugarFilmesEntity aluguel = new AlugarFilmesEntity();

            aluguel.setUsers(usuario);
            aluguel.setFilmes(filme);
            aluguel.setTipoPagamento(pagamento);
            aluguel.setDataInicio(dataInicio);
            aluguel.setDataDevolucao(dataDevolucao);
            aluguel.setValor(filme.getPrecoAluguel());
            aluguel.setStatus("ATIVO");

            alugarFilmesService.salvarAlugar(aluguel);

        } else {
            return "redirect:/escolher-filme";
        }

        return "redirect:/historico-compras";
    }

}
