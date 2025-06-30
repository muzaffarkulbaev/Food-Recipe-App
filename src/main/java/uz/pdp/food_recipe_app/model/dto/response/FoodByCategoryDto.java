package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class FoodByCategoryDto {

    String name;

    Float rating;

    Short cookingTime;

    Long foodPhotoId;

}
