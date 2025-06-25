package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

@Value
public class FoodAddDto {
    String title;
    String imageUrl;
    int cookingTime;
    String chefName;
    Float rating;
}
