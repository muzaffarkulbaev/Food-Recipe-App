package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class NewFoodDto {
    String name;
    Long foodPhotoId;
    Short cookingTime;
    Long chefPhotoId;
    String chefName;
    Float rating;
}
