package uz.pdp.food_recipe_app.service.abstractions;


import uz.pdp.food_recipe_app.model.dto.response.UserProfileDto;
import uz.pdp.food_recipe_app.model.dto.response.UserProfileRecipeDto;

import java.util.List;

public interface UserService {

    UserProfileDto getUserProfileData(Long userId);

    List<UserProfileRecipeDto> getUserProfileRecipesDataById(Long id);

}
