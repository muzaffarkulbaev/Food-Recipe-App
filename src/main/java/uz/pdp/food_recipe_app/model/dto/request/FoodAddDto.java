package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;
import uz.pdp.food_recipe_app.model.entity.Ingredient;

import java.util.List;

@Value
public class FoodAddDto {
    String name;
    String description;
    String imageUrl;
    Short cookingTime;
    Long chefId;
    List<IngredientDto> ingredients;
}
