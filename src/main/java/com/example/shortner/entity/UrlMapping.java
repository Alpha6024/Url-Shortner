package com.example.shortner.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="url_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlMapping{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="short_url",nullable=false,unique=true,length=20)
    private String shortUrl;

    @Column(name="org_url",nullable=false,columnDefinition="Text")
    private String orgUrl;

    @Column(name="created")
    private LocalDateTime created;

    @Column(name="clickcount")
    private Long clickCount;

    @PrePersist
    public void onCreate(){
        this.created=LocalDateTime.now();
        this.clickCount=0L;
    }
}
