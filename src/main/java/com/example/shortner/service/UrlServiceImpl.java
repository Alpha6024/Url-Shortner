package com.example.shortner.service;

import org.springframework.beans.factory.annotation.Value;
import com.example.shortner.entity.UrlMapping;
import com.example.shortner.dto.UrlRequest;
import com.example.shortner.dto.UrlResponse;
import com.example.shortner.repository.UrlRepository;
import com.example.shortner.util.ShortUrlGenerator;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import com.example.shortner.exception.DuplicateShortCodeException;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.concurrent.TimeUnit;
import lombok.*;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService{
    private final UrlRepository repository;
    private final RedisTemplate<String, String> redisTemplate;  

    @Override
    public UrlResponse shorturl(UrlRequest url){
        String shortcode=ShortUrlGenerator.generate(6);
        if(repository.existsByShortUrl(shortcode)){
        throw new DuplicateShortCodeException(
            "Please try again.");
        }
        UrlMapping urlMapping=UrlMapping.builder()
                .shortUrl(shortcode)
                .orgUrl(url.getUrl())
                .build();

        repository.save(urlMapping);
        
        redisTemplate.opsForValue().set(
        shortcode,
        url.getUrl(),
        24,
        TimeUnit.HOURS);
        return new UrlResponse(
        "http://localhost:8080/api/" + shortcode);
    }
    @Override
    public String getOrignalurl(String shorturl){

        String cachedurl=redisTemplate.opsForValue().get(shorturl);
        if(cachedurl != null){
            System.out.println("Fetching from cache");
            return cachedurl;
        }
        System.out.println("Fetching from DB");
        UrlMapping urlMapping = repository.findByShortUrl(shorturl)
            .orElseThrow(() -> new RuntimeException("Short URL not found"));

        redisTemplate.opsForValue().set(
            shorturl,
            urlMapping.getOrgUrl(),
            24,
            TimeUnit.HOURS);
        
        return urlMapping.getOrgUrl();
    }
}