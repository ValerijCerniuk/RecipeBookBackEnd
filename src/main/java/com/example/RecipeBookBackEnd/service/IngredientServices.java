package com.example.RecipeBookBackEnd.service;


import com.example.RecipeBookBackEnd.model.Ingredient;
import com.example.RecipeBookBackEnd.repository.IngredientRepository;
import com.example.RecipeBookBackEnd.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServices {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    public ResponseEntity<Ingredient> addIngredient(Ingredient ingredient) {
        if(ingredient != null) {
            return new ResponseEntity<>(ingredientRepository.save(ingredient),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Ingredient>> findAllIngredients() {
        return new ResponseEntity<>(ingredientRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<List<Ingredient>> findAllIngredientsByRecipeId(Long recipeId){
        if(!recipeRepository.existsById(recipeId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(ingredientRepository.findIngredientsByRecipesId(recipeId),
                    HttpStatus.OK);
        }
    }
}
