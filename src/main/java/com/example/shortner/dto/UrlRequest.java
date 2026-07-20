package com.example.shortner.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest{
    @NotBlank(message="Url can't be blank")
    private String url;
}