package com.example.RecipeBookBackEnd.repository;

import com.example.RecipeBookBackEnd.model.RecipeStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeStageRepository extends JpaRepository<RecipeStage, Long> {
}
