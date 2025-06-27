package uz.pdp.food_recipe_app.service.abstractions;


import uz.pdp.food_recipe_app.model.dto.request.UserDto;
import uz.pdp.food_recipe_app.model.dto.response.UserProfileDto;

public interface UserService {

    UserDto getUser(int userId);

    UserProfileDto getUserProfileData(Long userId);

}
