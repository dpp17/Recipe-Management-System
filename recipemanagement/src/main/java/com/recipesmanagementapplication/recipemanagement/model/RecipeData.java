package com.recipesmanagementapplication.recipemanagement.model;

import com.recipesmanagementapplication.recipemanagement.dto.RecipeDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "RecipeTable")
@NoArgsConstructor
public class RecipeData {
    /*
    id: A unique identifier for the recipe.
    name: The name of the recipe.
    description: A description of the recipe.
    ingredients: A list of ingredients required for the recipe, along with their quantities.
    instructions: A list of instructions for preparing the recipe.
    */
    @Id
    @GeneratedValue
    private int recipeId;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "ingredientsTable", joinColumns = @JoinColumn(name="id"))
    @Column(name = "ingredients")
    private List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "instructionsTable", joinColumns = @JoinColumn(name="id"))
    @Column(name = "instructions")
    private List<String> instructions;

    public void updateRecipeData(RecipeDTO recipeDTO) {
        this.name = recipeDTO.getName();
        this.description = recipeDTO.getDescription();
        this.ingredients = recipeDTO.getIngredients();
        this.instructions = recipeDTO.getInstructions();
    }

    public RecipeData(RecipeDTO recipeDTO){
        this.updateRecipeData(recipeDTO);
    }
}
