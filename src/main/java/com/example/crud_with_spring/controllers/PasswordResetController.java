package com.example.crud_with_spring.controllers;

import com.example.crud_with_spring.infra.security.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordResetController {

    @Autowired
    private PessoaRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        String email = tokenService.validateToken(token); //verifica se o token é valido
        if (email == null) {
            return "Token inválido ou expirado.";
        }

        Pessoa user = (Pessoa) userRepository.findByEmail(email);
        user.setSenha(passwordEncoder.encode(newPassword));//encoda a nova senha
        userRepository.save(user);//faz o update da senha

        return "Senha alterada com sucesso.";
    }
}
