package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class FoodResponceDto {
    String name;
    String chefName;
    Float rating;
    String photoUrl;
}
