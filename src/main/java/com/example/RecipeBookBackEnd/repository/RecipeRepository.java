package com.example.RecipeBookBackEnd.repository;


import com.example.RecipeBookBackEnd.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
