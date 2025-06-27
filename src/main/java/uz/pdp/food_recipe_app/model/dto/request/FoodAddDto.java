package uz.pdp.food_recipe_app.model.dto.request;

import jakarta.persistence.ManyToMany;
import lombok.Value;
import uz.pdp.food_recipe_app.model.entity.User;

import java.util.List;

@Value
public class FoodAddDto {
    String name;
    String description;
    String imageUrl;
    Short cookingTime;
    List<IngredientDto> ingredients;

    @ManyToMany
    User user;

}
