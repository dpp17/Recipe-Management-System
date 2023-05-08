package com.recipesmanagementapplication.recipemanagement.services;

import com.recipesmanagementapplication.recipemanagement.dto.RecipeDTO;
import com.recipesmanagementapplication.recipemanagement.dto.ResponseDTO;
import com.recipesmanagementapplication.recipemanagement.model.RecipeData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRecipeBusinessLogics {
    ResponseEntity<ResponseDTO> addRecipe(RecipeDTO recipeDTO);

    ResponseEntity<ResponseDTO> updateRecipeById(RecipeDTO recipeDTO, int recipeId);

    ResponseEntity<ResponseDTO> getRecipeById(int recipeId);

    String deleteRecipeById(int recipeId);

    List<RecipeData> getAllRecipeById();

    ResponseEntity<ResponseDTO> addIngredientsById(List<String> ingredients, int recipeId);

    ResponseEntity<ResponseDTO> removeIngredientsById(List<String> ingredients, int recipeId);

    List<RecipeData> filterByIngredients(List<String> ingredient);
}
