package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

import java.util.List;

@Value
public class FoodAddDto {

    String name;

    String description;

    String imageUrl;

    Short cookingTime;

    Long userId;

    List<IngredientDto> ingredients;

}
