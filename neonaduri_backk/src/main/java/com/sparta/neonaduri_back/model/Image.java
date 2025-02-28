package com.sparta.neonaduri_back.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String imageUrl;

    public Image(String filename, String imageUrl) {
        this.filename = filename;
        this.imageUrl = imageUrl;
    }
}