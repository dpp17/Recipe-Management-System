package com.recipesmanagementapplication.recipemanagement.exceptions;

public class RecipeIDNotFoundException extends RuntimeException{
    public RecipeIDNotFoundException(String message) {
        super(message);
    }
}
