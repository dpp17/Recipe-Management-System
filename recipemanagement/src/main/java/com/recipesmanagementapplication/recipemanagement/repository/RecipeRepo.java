package com.recipesmanagementapplication.recipemanagement.repository;

import com.recipesmanagementapplication.recipemanagement.model.RecipeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepo extends JpaRepository<RecipeData,Integer> {
    @Query(value = "select * from recipes.recipe_table where name =:name", nativeQuery = true)
    Optional<RecipeData> findRecipeByName(String name);
    @Query(value = "select * from recipes.recipe_table where name =:name", nativeQuery = true)
    RecipeData findRecipeByNames(String name);
}
