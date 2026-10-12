package com.PI.cinemix.service;

import com.PI.cinemix.entity.UserType;
import com.PI.cinemix.entity.UsersEntity;
import com.PI.cinemix.repository.UsersRepository;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    //POST
    public UsersEntity cadastrarUsuario(UsersEntity user) {
        user.setId(null);

        String email = user.getEmail().trim().toLowerCase(Locale.ROOT);

        if (email.startsWith("adm")) {
            user.setUserType(UserType.ADM);
        } else {
            user.setUserType(UserType.USUARIO);
        }

        return usersRepository.save(user);
    }

    //GET Email
    public UsersEntity getUserByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    //GET Id
    public UsersEntity getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    //PUT
    public UsersEntity atualizarDados(Long id, UsersEntity userRequest) {
        UsersEntity user = usersRepository.findById(id)
                .orElseThrow(()
                        -> new RuntimeException("Usuário não encontrado"));

        user.setNome(userRequest.getNome());
        user.setEmail(userRequest.getEmail());
        
        if (userRequest.getSenha() != null
                && !userRequest.getSenha().isBlank()) {
            user.setSenha(userRequest.getSenha());
        }
        
        user.setTelefone(userRequest.getTelefone());
        user.setCidade(userRequest.getCidade());

        return usersRepository.save(user);
    }

    //DELETE
    public void deletarUsuario(Long id) {
        UsersEntity user = getUserById(id);

        usersRepository.deleteById(user.getId());
    }
}
