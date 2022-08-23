package com.example.RecipeBookBackEnd;

import com.example.RecipeBookBackEnd.model.Ingredient;
import com.example.RecipeBookBackEnd.model.Photo;
import com.example.RecipeBookBackEnd.model.Recipe;
import com.example.RecipeBookBackEnd.model.RecipeStage;
import com.example.RecipeBookBackEnd.repository.ImageDbRepository;
import com.example.RecipeBookBackEnd.repository.IngredientRepository;
import com.example.RecipeBookBackEnd.repository.RecipeRepository;
import com.example.RecipeBookBackEnd.repository.RecipeStageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RecipeBookBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeBookBackEndApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RecipeRepository recipeRepository,
										IngredientRepository ingredientRepository,
										RecipeStageRepository recipeStageRepository,
										ImageDbRepository imageDbRepository
	)
	{
		return args -> {

			ingredientRepository.save(Ingredient.builder()
					.name("Cukrus")
					.amount("1 stikline")
					.description("Saldus smelis")
					.build());
			ingredientRepository.save(Ingredient.builder()
					.name("Miltai")
					.amount("0,5 kg.")
					.description("Malti grudai")
					.build());
			ingredientRepository.save(Ingredient.builder()
					.name("Kiausinei")
					.amount("2 vnt.")
					.description("Baltimu saltinis")
					.build());
			ingredientRepository.save(Ingredient.builder()
					.name("Druska")
					.amount("0.5 mazo sauksto")
					.description("Miniralas ligus mirciai")
					.build());
			Set<Ingredient> ingredientSet = new HashSet<>();
			ingredientSet.add(ingredientRepository.findByName("Cukrus").get());
			ingredientSet.add(ingredientRepository.findByName("Miltai").get());
			ingredientSet.add(ingredientRepository.findByName("Kiausinei").get());
			ingredientSet.add(ingredientRepository.findByName("Druska").get());

			recipeStageRepository.save(RecipeStage.builder()
					.instruction("Sumaisiti")
					.instructionOrder(1)
					.build());
			recipeStageRepository.save(RecipeStage.builder()
					.instruction("Paberti")
					.instructionOrder(2)
					.build());
			Set<RecipeStage> recipeStageSet = new HashSet<>();
			recipeStageSet.add(recipeStageRepository.findById(1L).get());
			recipeStageSet.add(recipeStageRepository.findById(2L).get());

//			FoodSort foodSortMeat = FoodSort.builder()
//					.name(EFoodSorts.MEAT)
//					.build();
//			FoodSort foodSortSalat = FoodSort.builder()
//					.name(EFoodSorts.SALAT)
//					.build();
//			FoodSort foodSortVegetable = FoodSort.builder()
//					.name(EFoodSorts.VEGETABLE)
//					.build();

			Photo defaultPhoto1 = Photo.builder()
					.name("productDefault1.jpg")
					.location("/home/valerij/IdeaProjects/RecipeBookBackEnd/src/main/resources/files/productDefault1.jpg")
					.build();

			Photo defaultPhoto2 = Photo.builder()
					.name("productDefault2.jpg")
					.location("/home/valerij/IdeaProjects/RecipeBookBackEnd/src/main/resources/files/productDefault2.jpg")
					.build();

			imageDbRepository.save(defaultPhoto1);
			imageDbRepository.save(defaultPhoto2);

			recipeRepository.save(Recipe.builder()
					.name("1.Salotos")
					.description("Vegetaru maistas")
							.photo1("1")
							.photo2("2")
					.ingredients(ingredientSet)
					.recipeStages(recipeStageSet)
					.build());
			recipeRepository.save(Recipe.builder()
					.name("2.Salotos su mesa")
					.description("Vyru maistas")
					.ingredients(ingredientSet)
					.recipeStages(recipeStageSet)
					.build());
			recipeRepository.save(Recipe.builder()
					.name("3.Vaisiu salotos")
					.description("Merginu maistas")
					.ingredients(ingredientSet)
					.recipeStages(recipeStageSet)
					.build());
			recipeRepository.save(Recipe.builder()
					.name("4.Salotos 2")
					.description("Vegetaru maistas")
					.ingredients(ingredientSet)
					.recipeStages(recipeStageSet)
					.build());
			recipeRepository.save(Recipe.builder()
					.name("5.Salotos su mesa 2")
					.description("Vyru maistas")
					.ingredients(ingredientSet)
					.recipeStages(recipeStageSet)
					.build());
			recipeRepository.save(Recipe.builder()
					.name("6.Vaisiu salotos 2")
					.description("Merginu maistas")
					.ingredients(ingredientSet)
					.recipeStages(recipeStageSet)
					.build());
		};
	}

}
