package com.recipesmanagementapplication.recipemanagement.controller;

import com.recipesmanagementapplication.recipemanagement.dto.RecipeDTO;
import com.recipesmanagementapplication.recipemanagement.dto.ResponseDTO;
import com.recipesmanagementapplication.recipemanagement.model.RecipeData;
import com.recipesmanagementapplication.recipemanagement.services.IRecipeBusinessLogics;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private IRecipeBusinessLogics iRecipeBusinessLogics;

    @PostMapping("/addRecipe")
    public ResponseEntity<ResponseDTO> addRecipe(@Valid @RequestBody RecipeDTO recipeDTO){
        return iRecipeBusinessLogics.addRecipe(recipeDTO);
    }

    @PutMapping("/updateRecipe")
    public ResponseEntity<ResponseDTO> updateRecipeById(@NotNull(message = "Recipe ID can't be null..") @RequestParam int recipeId,@Valid @RequestBody RecipeDTO recipeDTO){
        return iRecipeBusinessLogics.updateRecipeById(recipeDTO, recipeId);
    }

    @GetMapping("/getRecipe")
    public ResponseEntity<ResponseDTO> getRecipeById(@NotNull(message = "Recipe ID can't be null..") @RequestParam int recipeId){
        return iRecipeBusinessLogics.getRecipeById(recipeId);
    }

    @DeleteMapping("/deleteRecipe")
    public String deleteRecipeById(@NotNull(message = "Recipe ID can't be null..") @RequestParam int recipeId){
        return iRecipeBusinessLogics.deleteRecipeById(recipeId);
    }

    @GetMapping("/getAllRecipes")
    public List<RecipeData> getAllRecipeById(){
        return iRecipeBusinessLogics.getAllRecipeById();
    }

    /////////////////////////////////////////////////////////////////////////////////////
    //                                    Add Ingredient                               //
    /////////////////////////////////////////////////////////////////////////////////////

    @PutMapping("/addIngredients")
    public ResponseEntity<ResponseDTO> addIngredientsById(@NotNull(message = "Recipe ID can't be null..") @RequestParam int recipeId,@NotNull(message = "Ingredients can't be null..") @RequestParam List<String> ingredients){
        return iRecipeBusinessLogics.addIngredientsById(ingredients,recipeId);
    }

    @PutMapping("/removeIngredients")
    public ResponseEntity<ResponseDTO> removeIngredientsById(@NotNull(message = "Recipe ID can't be null..") @RequestParam int recipeId,@NotNull(message = "Ingredients can't be null..") @RequestParam List<String> ingredients){
        return iRecipeBusinessLogics.removeIngredientsById(ingredients,recipeId);
    }

    @GetMapping("/filterByIngredients")
    public ResponseEntity<ResponseDTO> filterByIngredients(@NotNull(message = "Ingredients can't be null..") @RequestParam List<String> ingredient){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("All Recipes with filtered ingredients",iRecipeBusinessLogics.filterByIngredients(ingredient)), HttpStatus.FOUND);
    }
}
