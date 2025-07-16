package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

import java.util.List;

@Value
public class FoodAddDto {

    String name;

    Long categoryId;

    Long photoId;

    Short cookingTime;

    Long userId;

    List<IngredientDto> ingredients;

}