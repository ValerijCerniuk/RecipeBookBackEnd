package com.example.RecipeBookBackEnd.controller;


import com.example.RecipeBookBackEnd.model.Ingredient;
import com.example.RecipeBookBackEnd.service.IngredientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientController {

    @Autowired
    private IngredientServices ingredientServices;

    @GetMapping("ingredients/")
    public ResponseEntity<List<Ingredient>> getAllIngredient(){
        return ingredientServices.findAllIngredients();
    }

    @PostMapping("ingredients/")
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
        return ingredientServices.addIngredient(ingredient);
    }


}
