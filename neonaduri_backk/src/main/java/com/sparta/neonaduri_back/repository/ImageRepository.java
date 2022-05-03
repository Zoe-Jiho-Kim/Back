package com.sparta.neonaduri_back.repository;

import com.sparta.neonaduri_back.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageUrl(String imageUrl);
    void deleteByFilename(String filename);

}