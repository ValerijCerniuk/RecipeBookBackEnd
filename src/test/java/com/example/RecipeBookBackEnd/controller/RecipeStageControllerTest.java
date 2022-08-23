package com.example.RecipeBookBackEnd.controller;

import com.example.RecipeBookBackEnd.model.Recipe;
import com.example.RecipeBookBackEnd.model.RecipeStage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeStageControllerTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    RecipeStageController recipeStageController;

    @Test
    void getAllStages() {
        Set<Recipe> recipeSet = new HashSet<>();
        RecipeStage stage1 = RecipeStage.builder()
                .recipes(recipeSet)
                .instructionOrder(1)
                .instruction("Do something 1")
                .build();
        RecipeStage stage2 = RecipeStage.builder()
                .recipes(recipeSet)
                .instructionOrder(2)
                .instruction("Do something 2")
                .build();
        RecipeStage stage3 = RecipeStage.builder()
                .recipes(recipeSet)
                .instructionOrder(3)
                .instruction("Do something 3")
                .build();
        entityManager.persist(stage1);
        entityManager.persist(stage2);
        entityManager.persist(stage3);
        Iterable<RecipeStage> recipeStages = recipeStageController.getAllStages().getBody();
        assertThat(recipeStages).hasSize(3).contains(stage1,stage2,stage3);
    }

    @Test
    void addIngredient() {
    }
}