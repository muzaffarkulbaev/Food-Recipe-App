package uz.pdp.food_recipe_app.service.abstractions;


import uz.pdp.food_recipe_app.model.dto.request.UserDto;

public interface UserService {

    UserDto getUser(int userId);
}
