package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.FoodAddDto;
import uz.pdp.food_recipe_app.model.dto.response.FoodByCategoryDto;
import uz.pdp.food_recipe_app.model.dto.response.NewFoodsListDto;
import uz.pdp.food_recipe_app.model.entity.Food;
import uz.pdp.food_recipe_app.model.entity.FoodIngredient;
import uz.pdp.food_recipe_app.model.entity.Ingredient;
import uz.pdp.food_recipe_app.repo.FoodIngredientRepo;
import uz.pdp.food_recipe_app.repo.FoodRepository;
import uz.pdp.food_recipe_app.repo.IngredientRepository;
import uz.pdp.food_recipe_app.service.abstractions.FoodService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;
    private final FoodIngredientRepo foodIngredientRepo;

    @Override
    public List<FoodByCategoryDto> getAllFoods() {
        return foodRepository.findAll().stream()
                .map(food -> new FoodByCategoryDto(
                        food.getName(),
                        food.getRating(),
                        food.getCookingTime(),
                        food.getAttachment().getId()
                ))
                .toList();
    }


    @Override
    public List<FoodByCategoryDto> getFoodsByCategory(Long categoryId) {
        List<Food> foods = foodRepository.findByCategoryId(categoryId);
        return foods.stream()
                .map(food -> new FoodByCategoryDto(
                        food.getName(),
                        food.getRating(),
                        food.getCookingTime(),
                        food.getAttachment().getId()
                ))
                .toList();
    }

    @Override
    public List<NewFoodsListDto> getNewFoods() {
        List<Food> newFoods = foodRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")); // sorted by newest first

        return newFoods.stream()
                .limit(10)
                .map(food -> new NewFoodsListDto(
                        food.getName(),
                        food.getAttachment().getId(),
                        food.getCookingTime(),
                        food.getChef().getAttachment().getId(),
                        food.getChef().getName(),
                        food.getRating()
                ))
                .toList();
    }

    @Override
    public void addNewFood(FoodAddDto foodAddDto) {
        Food newFood = Food.builder()
                .name(foodAddDto.getName())
                .description(foodAddDto.getDescription())
                .cookingTime(foodAddDto.getCookingTime())
                .build();
        foodRepository.save(newFood);
        foodRepository.save(newFood);

        foodAddDto.getIngredients().forEach(ingredient -> {
            if(ingredientRepository.findByName(ingredient.getName()) == null) {

                Ingredient newIngredient = Ingredient.builder()
                        .name(ingredient.getName())
                        .build();

                Ingredient savedIngredient = ingredientRepository.save(newIngredient);

                FoodIngredient foodIngredient = FoodIngredient.builder()
                        .quantity(ingredient.getQuantity())
                        .ingredient(savedIngredient)
                        .food(newFood)
                        .build();
                foodIngredientRepo.save(foodIngredient);
            }else {
                Ingredient existIngredient = ingredientRepository.findByName(ingredient.getName());
                FoodIngredient foodIngredient = FoodIngredient.builder()
                        .quantity(ingredient.getQuantity())
                        .ingredient(existIngredient)
                        .food(newFood)
                        .build();
                foodIngredientRepo.save(foodIngredient);
            }
        });
    }

    @Override
    public List<Food> getFoodByUserId(Long userId) {
        return List.of();
    }


//    @Override
//    public DashboardDto getDashboardData() {

//        User user = userRepository.findById(1)
//                .orElseThrow(() -> new RuntimeException("User not found"));

//        List<Food> featured = recipeRepository.findTop2ByOrderByRatingDesc();
//        List<Food> newRecipes = recipeRepository.findTop5ByOrderByCreatedAtDesc();

//        return DashboardDto.builder()
//                .user(UserProfileDto.builder()
//                        .name(user.getName())
//                       // .avatarUrl(user.getAvatarUrl())
//                        .build())
//                .categories(List.of("All", "Indian", "Italian", "Asian", "Chinese"))
//                .featuredRecipes(featured.stream().map(FoodDto::from).toList())
//                .newRecipes(newRecipes.stream().map(FoodDto::from).toList())
//                .build();
//        return null;
//    }
}
