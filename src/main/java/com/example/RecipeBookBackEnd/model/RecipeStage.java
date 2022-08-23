package com.example.RecipeBookBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeStage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipeStage_generator")
    private Long id;
    @NonNull
    private int instructionOrder;
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String instruction;


    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "recipeStages")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonIgnore
    private Set<Recipe> recipes;


}
