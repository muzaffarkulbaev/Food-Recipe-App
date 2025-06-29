package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.food_recipe_app.model.dto.response.UserProfileRecipeDto;
import uz.pdp.food_recipe_app.model.dto.request.UserDto;
import uz.pdp.food_recipe_app.model.dto.response.UserProfileDto;
import uz.pdp.food_recipe_app.model.entity.Food;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repo.FoodRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.FollowService;
import uz.pdp.food_recipe_app.service.abstractions.FoodService;
import uz.pdp.food_recipe_app.service.abstractions.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FoodService foodService;
    private final FollowService followService;
    private final FoodRepository foodRepository;

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public UserProfileDto getUserProfileData(Long userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = byId.get();

        String name = user.getName();
        String bio = user.getBio();
//        Long photoId = user.getAttachment().getId();
        Long photoId = 1L;
        List<Food> recipes = foodService.getFoodByUserId(userId);
        Integer followersAmount = followService.findFollowersById(userId);
        Integer followingsAmount = followService.findFollowingsById(userId);
        return new UserProfileDto(name, photoId, bio, followersAmount, followingsAmount, recipes.size());
    }

    @Override
    public List<UserProfileRecipeDto> getUserProfileRecipesDataById(Long id) {
        List<Food> userAllFood = foodRepository.findAllByUserId(id);
        if (userAllFood.isEmpty()) {
            throw new RuntimeException("Food not found");
        }

        return UserProfileRecipeDto.toDTOS(userAllFood);
    }
}
