package com.example.crud_with_spring.controllers;


import com.example.crud_with_spring.DTOS.AuthenticationDTO;
import com.example.crud_with_spring.DTOS.LoginResponseDTO;
import com.example.crud_with_spring.DTOS.RegisterDTO;
import com.example.crud_with_spring.entities.Pessoa;
import com.example.crud_with_spring.infra.security.TokenService;
import com.example.crud_with_spring.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PessoaRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO body){
        var userPass = new UsernamePasswordAuthenticationToken(body.email(),body.senha());
        var auth = this.authenticationManager.authenticate(userPass);

        var token = tokenService.generateToken((Pessoa) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO login){
        if(this.repository.findByEmail(login.email()) != null)return ResponseEntity.badRequest().build();

        String encryptedPass = new BCryptPasswordEncoder().encode(login.senha());
        Pessoa pessoa = new Pessoa(login.nome(),login.email(),encryptedPass, login.role());
        this.repository.save(pessoa);

        var token = tokenService.generateToken(pessoa);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


}
