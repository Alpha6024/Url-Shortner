package com.example.shortner.config;

import com.example.shortner.repository.UrlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner{
    private UrlRepository repository;
    public Startup(UrlRepository repository){
        this.repository=repository;
    }
    @Override
    public void run(String... args){
        System.out.println("Application Started..");
        System.out.println(repository.count());
    }
}