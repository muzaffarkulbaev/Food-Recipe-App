package uz.pdp.food_recipe_app.service.abstractions;


import uz.pdp.food_recipe_app.model.dto.request.IngredientNewDto;

public interface IngredientService {
    void addIngredient(IngredientNewDto ingredient);
}
