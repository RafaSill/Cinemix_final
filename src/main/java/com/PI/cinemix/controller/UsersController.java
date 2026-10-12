package com.PI.cinemix.controller;

import com.PI.cinemix.entity.UserType;
import com.PI.cinemix.entity.UsersEntity;
import com.PI.cinemix.repository.UsersRepository;
import com.PI.cinemix.service.FilmesService;
import com.PI.cinemix.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsersController {

    @Autowired
    UsersService userService;

    @Autowired
    FilmesService filmesService;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/")
    public String ViewLoginPage(Model model) {
        model.addAttribute("user", new UsersEntity());
        return "login";
    }

    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        model.addAttribute("user", new UsersEntity());

        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastro(@ModelAttribute("user") UsersEntity user, Model model) {

        if (userService.getUserByEmail(user.getEmail()) != null) {
            model.addAttribute("erro", "Usuário já cadastrado!");
            return "cadastro";
        }
        userService.cadastrarUsuario(user);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") UsersEntity dadosLogin, HttpSession session, Model model) {
        UsersEntity usuarioBanco = userService.getUserByEmail(dadosLogin.getEmail());

        if (usuarioBanco == null) {
            model.addAttribute("erro", "E-mail não encontrado.");

            return "login";
        }

        if (usuarioBanco.getSenha() == null
                || dadosLogin.getSenha() == null
                || !usuarioBanco.getSenha().equals(dadosLogin.getSenha())) {

            model.addAttribute("erro", "Senha incorreta");
            return "login";
        }

        session.setAttribute("usuarioId", usuarioBanco.getId());
        session.setAttribute("userType", usuarioBanco.getUserType());

        return "redirect:/home";
    }

    @GetMapping("/atualizar-dados")
    public String abrirAtualizarDados(HttpSession session, Model model) {

        Long usuarioId = (Long) session.getAttribute("usuarioId");

        if (usuarioId == null) {
            return "redirect:/";
        }

        UsersEntity usuario = usersRepository.findById(usuarioId)
                .orElseThrow(()
                        -> new RuntimeException("Usuário não encontrado"));

        model.addAttribute("usuario", usuario);

        return "atualizar-dados";
    }

    @PostMapping("/atualizar-dados")
    public String atualizarDados(
            @ModelAttribute("usuario") UsersEntity usuarioRequest,
            @RequestParam(required = false) String novaSenha,
            @RequestParam(required = false) String confirmarSenha,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Long usuarioId = (Long) session.getAttribute("usuarioId");

        if (usuarioId == null) {
            return "redirect:/login";
        }

        if (novaSenha != null && !novaSenha.isBlank()) {
            if (!novaSenha.equals(confirmarSenha)) {
                redirectAttributes.addFlashAttribute(
                        "erro", "As senhas não coincidem.");
                return "redirect:/atualizar-dados";
            }

            usuarioRequest.setSenha(novaSenha);
        } else {
            usuarioRequest.setSenha(null);
        }

        userService.atualizarDados(usuarioId, usuarioRequest);

        redirectAttributes.addFlashAttribute(
                "mensagem", "Dados atualizados com sucesso!");

        return "redirect:/atualizar-dados";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Long idUsuario = (Long) session.getAttribute("usuarioId");

        if (idUsuario == null) {
            return "redirect:/";
        }

        UsersEntity user = userService.getUserById(idUsuario);

        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.getUserType() == UserType.ADM);

        return "home";

    }

    @GetMapping("/comprar-alugar")
    public String comprarAlugar(Model model) {
        model.addAttribute("filmes", filmesService.listarTodosFilmes());
        model.addAttribute("filmesSelecionadoId", null);

        return "comprar-alugar";
    }
}
