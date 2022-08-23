package com.example.RecipeBookBackEnd.repository;


import com.example.RecipeBookBackEnd.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findIngredientsByRecipesId(Long recipeId);

    Optional<Ingredient> findByName(String name);
}
