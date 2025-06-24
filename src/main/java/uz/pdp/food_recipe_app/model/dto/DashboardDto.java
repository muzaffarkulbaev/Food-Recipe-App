package uz.pdp.food_recipe_app.model.dto;

import lombok.Value;

import java.util.List;

@Value
public class DashboardDto {
    UserProfileDto user;
    List<String> categories;
    List<FoodResponseDto> featuredRecipes;
    List<FoodResponseDto> newRecipes;
}
