package com.example.crud_with_spring.config;

import com.example.crud_with_spring.service.DBservice;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBservice dbService;


    @PostConstruct
    public void instanciaDB(){
        this.dbService.instanciaDB();
    }
}
