package com.PI.cinemix.controller;

import com.PI.cinemix.entity.UsersEntity;
import com.PI.cinemix.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UserRestController {

    @Autowired
    UsersService userService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsersEntity> cadastrarUsuario(@RequestBody UsersEntity user) {
        UsersEntity novoUsuario = userService.cadastrarUsuario(user);

        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UsersEntity dadosLogin, HttpSession session) {

        UsersEntity usuarioBanco = userService.getUserByEmail(dadosLogin.getEmail());

        if (usuarioBanco == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!usuarioBanco.getSenha().equals(dadosLogin.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        session.setAttribute("usuarioId", usuarioBanco.getId());
        session.setAttribute("tipo", usuarioBanco.getUserType());

        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        session.invalidate();
        
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/atualizaDados/{id}")
    public ResponseEntity<UsersEntity> atualizarDados(@PathVariable Long id, @RequestBody UsersEntity user){
        var dadosAtualizados = userService.atualizarDados(id, user);
        
        return new ResponseEntity<>(dadosAtualizados, HttpStatus.OK);
    }
}
