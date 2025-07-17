package uz.pdp.food_recipe_app.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.FilterDto;
import uz.pdp.food_recipe_app.model.dto.request.FoodAddDto;
import uz.pdp.food_recipe_app.model.dto.response.FoodByCategoryDto;
import uz.pdp.food_recipe_app.model.dto.response.FoodResponceDto;
import uz.pdp.food_recipe_app.model.dto.response.NewFoodsListDto;
import uz.pdp.food_recipe_app.model.entity.*;
import uz.pdp.food_recipe_app.repo.*;
import uz.pdp.food_recipe_app.service.abstractions.FoodService;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final IngredientRepository ingredientRepository;
    private final FoodIngredientRepository foodIngredientRepository;
    private final UserRepository userRepository;
    private final ProcedureRepository procedureRepository;
    private final AttachmentRepository attachmentRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<FoodResponceDto> getAllFoods() {
        return foodRepository.findAll().stream()
                .map(food -> new FoodResponceDto(
                        food.getName(),
                        food.getRating(),
                        food.getUser().getName(),
                        food.getCookingTime(),
                        food.getAttachment().getId()
                ))
                .toList();
    }

    @Override
    public List<FoodResponceDto> getFoodsByCategory(Long categoryId) {
        List<Food> foods = foodRepository.findByCategoryId(categoryId);
        return foods.stream()
                .map(food -> new FoodResponceDto(
                        food.getName(),
                        food.getRating(),
                        food.getUser().getName(),
                        food.getCookingTime(),
                        food.getAttachment().getId()
                ))
                .toList();
    }

    @Override
    public List<NewFoodsListDto> getNewFoods() {
        List<Food> newFoods = foodRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return newFoods.stream()
                .limit(10)
                .map(food -> new NewFoodsListDto(
                        food.getName(),
                        food.getAttachment().getId(),
                        food.getCookingTime(),
                        food.getUser().getAttachment().getId(),
                        food.getUser().getName(),
                        food.getRating()
                ))
                .toList();
    }

    @Override
    public void addNewFood(FoodAddDto foodAddDto, List<String> procedureList) {
        User newFoodUser = userRepository.findById(foodAddDto.getUserId()).orElse(null);
        Attachment attachment = attachmentRepository.findById(foodAddDto.getPhotoId()).orElseThrow();
        Category category = categoryRepository.findById(foodAddDto.getCategoryId()).orElseThrow();
        Food newFood = Food.builder()
                .name(foodAddDto.getName())
                .cookingTime(foodAddDto.getCookingTime())
                .user(newFoodUser)
                .attachment(attachment)
                .category(category)
                .build();
        foodRepository.save(newFood);

        Long foodIdForDesc = newFood.getId();

        short step = 1;
        for (String string : procedureList) {
            Procedure procedure = Procedure.builder()
                    .description(string)
                    .foodId(foodIdForDesc)
                    .step(step)
                    .build();
            procedureRepository.save(procedure);
            step++;
        }


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
                foodIngredientRepository.save(foodIngredient);
            }else {
                Ingredient existIngredient = ingredientRepository.findByName(ingredient.getName());
                FoodIngredient foodIngredient = FoodIngredient.builder()
                        .quantity(ingredient.getQuantity())
                        .ingredient(existIngredient)
                        .food(newFood)
                        .build();
                foodIngredientRepository.save(foodIngredient);
            }
        });
    }

    @Override
    public List<Procedure> getFoodProcedures(Long foodId) {
        return procedureRepository.getAllProceduresByFoodId(foodId);
    }

    @Override
    public List<FoodResponceDto> getSearchedFoods(String search) {

        String[] keywords = search.trim().split("\\s+");

        HashSet<Food> searchFoods = new HashSet<>();

        for (String keyword : keywords) {
            searchFoods.addAll(foodRepository.findByNameLike(keyword));
        }

        return searchFoods.stream()
                .sorted(Comparator.comparing(Food::getRating).reversed())
                .map(food -> new FoodResponceDto(
                        food.getName(),
                        food.getRating(),
                        food.getUser().getName(),
                        food.getCookingTime(),
                        food.getAttachment().getId()))
                .toList();
    }

    @Override
    public List<FoodResponceDto> getFoodsByFilter(FilterDto filterDto) {

        List<Food> foods = foodRepository.findByCategoryId(filterDto.getCategoryId());

        String time = filterDto.getTime();

        foods = switch (time){
            case "ALL" -> foods;
            case "NEWEST" -> foods.stream()
                    .filter(food -> food.getCreatedAt() != null)
                    .sorted(Comparator.comparing(Food::getCreatedAt).reversed())
                    .toList();
            case "OLDEST" -> foods.stream()
                    .filter(food -> food.getCreatedAt() != null)
                    .sorted(Comparator.comparing(Food::getCreatedAt))
                    .toList();
            case "POPULARITY" -> foods.stream().sorted(Comparator.comparing(Food::getViewAmount).reversed()).limit(10).toList();
            default -> throw new IllegalArgumentException("Unknown filter time: " + time);
        };

        return foods.stream().filter(food ->
                    filterDto.getRating() == null ||
                        food.getRating() >= filterDto.getRating()
                )
                .map(food ->
                new FoodResponceDto(
                        food.getName(),
                        food.getRating(),
                        food.getUser().getName(),
                        food.getCookingTime(),
                        food.getAttachment().getId()
                )
        ).toList();
    }

    @Override
    public List<FoodResponceDto> getFoodsByUserId(Long userId) {
        List<Food> foodList = foodRepository.findByUserId(userId);
        List<FoodResponceDto> responseDtos = foodList.stream()
                .map(food -> new FoodResponceDto(food.getName(),  food.getRating(),food.getUser().getName(),food.getCookingTime(), food.getAttachment().getId()))
                .toList();
        return responseDtos;
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
