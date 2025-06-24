package uz.pdp.food_recipe_app.model.dto;

import lombok.Value;

@Value
public class FoodResponseDto {
    String title;
    String imageUrl;
    double rating;
    int cookingTime;
}
