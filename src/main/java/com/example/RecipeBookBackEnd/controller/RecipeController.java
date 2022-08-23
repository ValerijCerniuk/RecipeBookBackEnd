package com.example.RecipeBookBackEnd.controller;

import com.example.RecipeBookBackEnd.model.Ingredient;
import com.example.RecipeBookBackEnd.model.Recipe;
import com.example.RecipeBookBackEnd.service.IngredientServices;
import com.example.RecipeBookBackEnd.service.RecipeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {

    @Autowired
    private RecipeServices recipeServices;
    @Autowired
    private IngredientServices ingredientServices;

    @GetMapping("recipes/")
    public ResponseEntity<List<Recipe>> getAllRecipe(){
        return recipeServices.findAll();
    }

    @PostMapping("recipes/")
    public ResponseEntity<Recipe> addCookingRecipe(@RequestBody Recipe recipe){
        return recipeServices.save(recipe);
    }

    @GetMapping("recipes/{recipeId}")
    public ResponseEntity<Recipe> getTutorialById(@PathVariable(name = "recipeId") Long recipeId){
        return recipeServices.findById(recipeId);
    }

    @GetMapping("recipes/{recipeId}/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredientsForRecipe(@PathVariable(name = "recipeId") Long recipeId){
        return ingredientServices.findAllIngredientsByRecipeId(recipeId);
    }

    @PostMapping("recipes/{recipeId}/ingredients")
    public ResponseEntity<Recipe> addIngredientToRecipe(@PathVariable(name = "recipeId") Long recipeId,
                                                            @RequestBody Ingredient ingredient){
        return recipeServices.recipeAddIngredient(recipeId, ingredient);
    }

    @DeleteMapping("recipes/{recipeId}")
    public ResponseEntity<HttpStatus> deleteRecipe(@PathVariable(name = "recipeId") Long recipeId){
        return recipeServices.deleteById(recipeId);
    }

    @PutMapping("recipes/{recipeId}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable (name = "recipeId") Long recipeId, @RequestBody Recipe recipe){
        return recipeServices.updateRecipe(recipeId, recipe);
    }
}
