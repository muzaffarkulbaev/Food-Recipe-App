package uz.pdp.food_recipe_app.service.abstractions;

import software.amazon.awssdk.services.s3.endpoints.internal.Value;
import uz.pdp.food_recipe_app.model.dto.request.FoodAddDto;
import uz.pdp.food_recipe_app.model.dto.response.FoodByCategoryDto;
import uz.pdp.food_recipe_app.model.dto.response.NewFoodsListDto;
import uz.pdp.food_recipe_app.model.entity.Food;
import uz.pdp.food_recipe_app.model.entity.Procedure;

import java.util.List;

public interface FoodService {

    List<FoodByCategoryDto> getAllFoods();

    List<FoodByCategoryDto> getFoodsByCategory(Long categoryId);

    List<NewFoodsListDto> getNewFoods();

    void addNewFood(FoodAddDto foodAddDto, List<String> procedureDtoList);

    List<Procedure> getFoodProcedures(Long foodId);

}
