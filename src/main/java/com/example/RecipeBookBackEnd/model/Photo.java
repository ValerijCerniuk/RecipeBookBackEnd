package com.example.RecipeBookBackEnd.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String location;

    public Photo() {
    }

    public Photo(Long id) {
        this.id = id;
    }

    public Photo(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Photo(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}