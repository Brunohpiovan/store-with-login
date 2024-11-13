package com.example.crud_with_spring.controllers;

import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.infra.security.TokenService;
import com.example.crud_with_spring.repositories.PessoaRepository;
import com.example.crud_with_spring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

//classe responsavel por receber uma requisicao para alterar a senha

@RestController
public class PasswordRecoveryController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/recover-password")
    public String recoverPassword(@RequestParam String email) {
        String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8).trim(); //decodifica o email para o padrao UTF8 e retira espacos
        if (decodedEmail.isEmpty()) {
            return "Endereço de e-mail inválido.";
        }
        Pessoa user = (Pessoa) repository.findByEmail(decodedEmail);

        String token = this.tokenService.generateToken(user);
        String recoveryUrl = "http://localhost:8080/reset-password?token=" + token; //url para alterar a senha
        emailService.sendEmail(decodedEmail, "Recuperação de Senha", "Clique no link para resetar sua senha: " + recoveryUrl); //envia um email para o destinatario

        return "Instruções de recuperação de senha enviadas para seu e-mail.";
    }
}

