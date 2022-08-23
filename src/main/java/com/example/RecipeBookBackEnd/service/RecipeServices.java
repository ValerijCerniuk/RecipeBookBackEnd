package com.example.RecipeBookBackEnd.service;


import com.example.RecipeBookBackEnd.model.Ingredient;
import com.example.RecipeBookBackEnd.model.Recipe;
import com.example.RecipeBookBackEnd.model.RecipeStage;
import com.example.RecipeBookBackEnd.repository.IngredientRepository;
import com.example.RecipeBookBackEnd.repository.RecipeRepository;
import com.example.RecipeBookBackEnd.repository.RecipeStageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServices {
    private final Logger LOGGER = LoggerFactory.getLogger(RecipeServices.class);

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private RecipeStageRepository recipeStageRepository;

    public ResponseEntity<List<Recipe>> findAll() {
        return new ResponseEntity<>(recipeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Recipe> save(Recipe recipeData) {
        if (recipeData != null) {
            Set<Ingredient> ingredientsToAdd = new HashSet<>();
            Set<Ingredient> ingredientsData = recipeData.getIngredients();
            Set<RecipeStage> recipeStagesToAdd = new HashSet<>();
            Set<RecipeStage> recipeStagesData = recipeData.getRecipeStages();
//            pernaudojami ingredientai, jai yra DB tai iraso Is Db, jai nera iraso i Db nauja
            for( Ingredient i : ingredientsData) {
                Example<Ingredient> ingredientExample = Example.of(i);
                if (ingredientRepository.findOne(ingredientExample).isPresent()) {
                    ingredientsToAdd.add(ingredientRepository.findOne(ingredientExample).get());
                } else {
                    ingredientRepository.save(i);
                    ingredientsToAdd.add(ingredientRepository.findOne(ingredientExample).get());
                }
            }
//          Visus stage iraso i Db.
            for( RecipeStage stage : recipeStagesData){
                   RecipeStage savedStage = recipeStageRepository.save(stage);
                   if(recipeStageRepository.findById(savedStage.getId()).isPresent()) {
                       recipeStagesToAdd.add(recipeStageRepository.findById(savedStage.getId()).get());
                   }
            }

            return new ResponseEntity<>(recipeRepository.save(Recipe.builder()
                    .name(recipeData.getName())
                    .description(recipeData.getDescription())
                    .ingredients(ingredientsToAdd)
                    .recipeStages(recipeStagesToAdd)
                    .build()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Recipe> findById(Long recipeId) {
        if (recipeId != null) {
            Optional<Recipe> _recipe = recipeRepository.findById(recipeId);
            return _recipe.map(recipe ->
                    new ResponseEntity<>(recipe, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<HttpStatus> deleteById(Long recipeId) {
        if (recipeId != null) {
            if (recipeRepository.findById(recipeId).isPresent()) {
                recipeRepository.deleteById(recipeId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Recipe> updateRecipe(Long recipeId, Recipe recipe) {
        if (recipeId != null) {
            Optional<Recipe> _recipe = recipeRepository.findById(recipeId);
            if (_recipe.isPresent()) {
                Recipe recipeData = _recipe.get();
                recipeData.setName(recipe.getName());
                recipeData.setDescription(recipe.getDescription());
                recipeData.setRecipeStages(recipe.getRecipeStages());
                recipeData.setIngredients(recipe.getIngredients());
                recipeRepository.save(recipeData);
                LOGGER.info("Recipe with id: " + recipeId + " was modified to: " + recipeData);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Recipe> recipeAddIngredient(Long recipeId, Ingredient ingredient) {
        if (recipeId != null) {
            Optional<Recipe> _recipe = recipeRepository.findById(recipeId);
            if (_recipe.isPresent()) {
                Recipe recipeData = _recipe.get();
                recipeData.addIngredient(ingredient);
                recipeRepository.save(recipeData);
                LOGGER.info("Ingredient " + ingredient + " was added to recipe " + recipeData.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
