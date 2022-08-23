package com.example.RecipeBookBackEnd.model;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cookingRecipe_generator")
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    private String photo1 ;

    private String photo2;

//TODO padarit Sarisi su valgio rusim kad darit palei tai pajeska i r rusiot receptus
//    private Set<FoodSort> foodSorts;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "recipe_stages",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "recipeStage_id"))
    private Set<RecipeStage> recipeStages = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "recipe_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients = new HashSet<>();


    public void addRecipeStage(RecipeStage recipeStage){
        this.recipeStages.add(recipeStage);
        recipeStage.getRecipes().add(this);
    }

    public void removeRecipeStage(Long id){
        RecipeStage recipeStage = this.recipeStages.stream()
                .filter(r -> Objects.equals(r.getId(), id))
                .findFirst()
                .orElse(null);
        if (recipeStage != null) {
            this.recipeStages.remove(recipeStage);
            recipeStage.getRecipes().remove(this);
        }
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        ingredient.getRecipes().add(this);

    }
    public void removeIngredient(Long id){
        Ingredient ingredient = this.ingredients.stream()
                .filter(i -> Objects.equals(i.getId(), id))
                .findFirst()
                .orElse(null);
        if (ingredient != null) {
            this.ingredients.remove(ingredient);
            ingredient.getRecipes().remove(this);
        }
    }
}
