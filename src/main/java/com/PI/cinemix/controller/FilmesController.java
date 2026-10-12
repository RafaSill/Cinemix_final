package com.PI.cinemix.controller;

import com.PI.cinemix.entity.FilmesEntity;
import com.PI.cinemix.entity.UserType;
import com.PI.cinemix.service.FilmesService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmesController {

    @Autowired
    FilmesService filmesService;

    @GetMapping("/catalogo")
    public String catalogo(
            @RequestParam(name = "genero", required = false, defaultValue = "") String genero,
            HttpSession session,
            Model model) {

        if (genero.isBlank()) {
            model.addAttribute("listarFilmes",
                    filmesService.listarTodosFilmes());
        } else {
            model.addAttribute("listarFilmes",
                    filmesService.buscarPorGenero(genero));
        }

        model.addAttribute("generoSelecionado", genero);

        boolean isAdmin = UserType.ADM.equals(
                session.getAttribute("userType"));

        model.addAttribute("isAdmin", isAdmin);

        return "catalogo";
    }

    @GetMapping("/escolher-filme")
    public String escolherFilmes(@RequestParam(name = "filmeId", required = false) Long filmeId, Model model) {
        model.addAttribute("filmes", filmesService.listarTodosFilmes());
        model.addAttribute("filmeSelecionadoId", filmeId);

        return "comprar-alugar";
    }

    @GetMapping("/adicionar-filme")
    public String abrirFilmeForm(Model model) {
        model.addAttribute("filme", new FilmesEntity());

        return "adicionar-filme";
    }

    @PostMapping("/adicionar-filme")
    public String adicionarFilme(@Valid @ModelAttribute("filme") FilmesEntity filme,
            BindingResult result) {

        if (result.hasErrors()) {
            return "adicionar-filme";
        }

        if (filme.getId() == null) {
            filmesService.adicionarFilmes(filme);
        } else {
            filmesService.atualizarFilme(filme.getId(), filme);
        }

        return "redirect:/catalogo";
    }

    @GetMapping("atualizarFilmeForm/{id}")
    public String atualizarFilme(@PathVariable(value = "id") Long id, Model model) {
        FilmesEntity filme = filmesService.getFilmeById(id);
        model.addAttribute("filme", filme);

        return "atualizar-filme";
    }

    @GetMapping("/deletarFilme/{id}")
    public String deletarFilme(@PathVariable(value = "id") Long id, Model model) {
        filmesService.deletarFilme(id);

        return "redirect:/catalogo";
    }
    
    
}
