package com.example.shortner.service;

import com.example.shortner.dto.UrlRequest;
import com.example.shortner.dto.UrlResponse;

public interface UrlService{
    UrlResponse shorturl(UrlRequest url);
    String getOrignalurl(String shorturl);
}