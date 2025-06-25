package uz.pdp.food_recipe_app.service.abstractions;


import uz.pdp.food_recipe_app.model.dto.UserDto;

public interface UserService {

    UserDto getUser(int userId);
}
