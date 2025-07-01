package uz.pdp.food_recipe_app.service.abstractions;


import uz.pdp.food_recipe_app.model.dto.response.UserProfileRecipeDto;
import uz.pdp.food_recipe_app.model.dto.request.UserDto;
import uz.pdp.food_recipe_app.model.dto.response.UserProfileDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long userId);

    UserProfileDto getUserProfileData(Long userId);

    List<UserProfileRecipeDto> getUserProfileRecipesDataById(Long id);

}
