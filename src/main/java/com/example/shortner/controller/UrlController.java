package com.example.shortner.controller;

import com.example.shortner.dto.UrlRequest;
import com.example.shortner.dto.UrlResponse;
import com.example.shortner.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class UrlController{
    private final UrlService urlservice;

    @PostMapping("/shorten")
    public UrlResponse shortenurl(@RequestBody UrlRequest request){
        return urlservice.shorturl(request);
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortcode){
        String originalUrl=urlservice.getOrignalurl(shortcode);
        return ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(originalUrl))
            .build();
    }
}
