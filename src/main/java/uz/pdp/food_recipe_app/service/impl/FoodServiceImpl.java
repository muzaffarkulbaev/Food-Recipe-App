package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.repositories.FoodRepository;
import uz.pdp.food_recipe_app.repositories.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.FoodService;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository recipeRepository;
    private final UserRepository userRepository;

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
