package com.recipesmanagementapplication.recipemanagement.services;

import com.recipesmanagementapplication.recipemanagement.dto.RecipeDTO;
import com.recipesmanagementapplication.recipemanagement.dto.ResponseDTO;
import com.recipesmanagementapplication.recipemanagement.exceptions.RecipeIDNotFoundException;
import com.recipesmanagementapplication.recipemanagement.model.RecipeData;
import com.recipesmanagementapplication.recipemanagement.repository.RecipeRepo;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeBusinessLogics implements IRecipeBusinessLogics{

    @Autowired
    private RecipeRepo recipeRepo;

    @Override
    public ResponseEntity<ResponseDTO> addRecipe(RecipeDTO recipeDTO) {
        Optional<RecipeData> recipe = recipeRepo.findRecipeByName(recipeDTO.getName());
        if(recipe.isEmpty()){
            RecipeData recipeData = new RecipeData(recipeDTO);
            recipeRepo.save(recipeData);
            return new ResponseEntity<>(new ResponseDTO("Recipe saved successfully..",recipeData), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseDTO(" ******** Recipe Already Exist.. ******** ",recipe), HttpStatus.FOUND);

//        RecipeData existingRecipe = recipeRepo.findRecipeByNames(recipeDTO.getName());
//        if(existingRecipe != null){
//            throw new RecipeIDNotFoundException("Recipe already exists with the name: " + recipeDTO.getName());
//        }
//        RecipeData recipeData = new RecipeData(recipeDTO);
//        recipeRepo.save(recipeData);
//        return new ResponseEntity<>(new ResponseDTO("Recipe saved successfully..",recipeData), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateRecipeById(RecipeDTO recipeDTO, int recipeId) {
        RecipeData recipeData = checkIfRecipeIsPresent(recipeId);
        recipeData.updateRecipeData(recipeDTO);
        recipeRepo.save(recipeData);
        return new ResponseEntity<>(new ResponseDTO("Recipe Updated successfully..",recipeData), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ResponseDTO> getRecipeById(int recipeId) {
        RecipeData recipeData = checkIfRecipeIsPresent(recipeId);
        return new ResponseEntity<>(new ResponseDTO("Recipe Detail..",recipeData), HttpStatus.FOUND);
    }

    @Override
    public String deleteRecipeById(int recipeId) {
        RecipeData recipeData = checkIfRecipeIsPresent(recipeId);
        recipeRepo.deleteById(recipeId);
        return "Recipe Deleted Successfully..";
    }

    @Override
    public List<RecipeData> getAllRecipeById() {
        return recipeRepo.findAll();
    }

    @Override
    public ResponseEntity<ResponseDTO> addIngredientsById(List<String> ingredients, int recipeId) {
        RecipeData recipeData = checkIfRecipeIsPresent(recipeId);
        ingredients.addAll(recipeData.getIngredients());
        recipeData.setIngredients(ingredients);
        recipeRepo.save(recipeData);
        return new ResponseEntity<>(new ResponseDTO("Ingredients added successfully..", recipeData), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO> removeIngredientsById(List<String> ingredients, int recipeId) {
        RecipeData recipeData = checkIfRecipeIsPresent(recipeId);
        List<String> stringList = recipeData.getIngredients();
        stringList.removeAll(ingredients);
        recipeData.setIngredients(stringList);
        recipeRepo.save(recipeData);
        return new ResponseEntity<>(new ResponseDTO("Ingredients removed successfully..", recipeData), HttpStatus.OK);
    }

    @Override
    public List<RecipeData> filterByIngredients(List<String> ingredient) {
        return recipeRepo.findAll().stream().filter(recipeData -> recipeData.getIngredients().stream().anyMatch(ingredient::contains)).distinct().collect(Collectors.toList());
    }
    private RecipeData checkIfRecipeIsPresent(int recipeId){
        return recipeRepo.findById(recipeId).orElseThrow(()-> new RecipeIDNotFoundException("Recipe with ID :: " + recipeId + " is NOT_FOUND"));
    }

}
