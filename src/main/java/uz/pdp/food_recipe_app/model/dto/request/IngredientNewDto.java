package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

@Value
public class IngredientNewDto {
    String name;
    Long photoId;
}
