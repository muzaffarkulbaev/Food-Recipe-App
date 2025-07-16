package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class FoodResponceDto {
    String name;
    Float rating;
    String chefName;
    Short cookingTime;
    Long photoId;
}
