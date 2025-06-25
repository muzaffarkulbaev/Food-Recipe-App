package uz.pdp.food_recipe_app.model.dto;

import lombok.Value;

@Value
public class FoodDto {
    String title;
    String imageUrl;
    double rating;
    int cookingTime;
}
