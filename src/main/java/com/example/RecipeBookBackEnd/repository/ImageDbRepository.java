package com.example.RecipeBookBackEnd.repository;

import com.example.RecipeBookBackEnd.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDbRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findByName(String name);
}