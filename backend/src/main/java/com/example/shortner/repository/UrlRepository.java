package com.example.shortner.repository;

import com.example.shortner.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping,Long>{
    Optional<UrlMapping> findByShortUrl(String shortUrl);
    boolean existsByShortUrl(String shortUrl);
}