package uz.pdp.food_recipe_app.service.abstractions;

import uz.pdp.food_recipe_app.model.dto.response.FoodByCategoryDto;
import uz.pdp.food_recipe_app.model.dto.response.NewFoodDto;
import uz.pdp.food_recipe_app.model.entity.Food;

import java.util.List;

public interface FoodService {

    List<FoodByCategoryDto> getAllFoods();
    List<FoodByCategoryDto> getFoodsByCategory(Long categoryId);
    List<NewFoodDto> getNewFoods();


}
