package com.example.RecipeBookBackEnd.model;

import org.hibernate.annotations.Cascade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_generator")
    private Long id;
    private String name;
//    TODO  pakeist String i Integer, padarit enum kiekio vienetams
    private String amount;
    private String description;


   @ManyToMany(fetch = FetchType.LAZY,
           mappedBy = "ingredients")
   @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
   @JsonIgnore
    private Set<Recipe> recipes;


}
