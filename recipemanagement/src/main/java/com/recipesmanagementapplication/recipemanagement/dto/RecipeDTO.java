package com.recipesmanagementapplication.recipemanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.List;

@Data
public class RecipeDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^([A-Z][A-Za-z]{2,}\\s)+$", message = "Start Name With Capital Letter")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Ingredients can't be null..")
    private List<String> ingredients;

    @NotNull(message = "Instructions can't be null..")
    private List<String> instructions;
}
