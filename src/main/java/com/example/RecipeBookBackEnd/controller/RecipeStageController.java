package com.example.RecipeBookBackEnd.controller;

import com.example.RecipeBookBackEnd.model.RecipeStage;
import com.example.RecipeBookBackEnd.repository.RecipeStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("api/")
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeStageController {
    @Autowired
    private RecipeStageRepository recipeStageRepository;

    @GetMapping("recipe-stages/")
    public ResponseEntity<List<RecipeStage>> getAllStages(){
        return new ResponseEntity<>(recipeStageRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("recipe-stages/")
    public ResponseEntity<RecipeStage> addIngredient(@RequestBody RecipeStage recipeStage){
        return new ResponseEntity<>(recipeStageRepository.save(recipeStage), HttpStatus.OK);
    }
}
