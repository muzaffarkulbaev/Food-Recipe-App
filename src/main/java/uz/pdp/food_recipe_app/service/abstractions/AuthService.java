package uz.pdp.food_recipe_app.service.abstractions;

import uz.pdp.food_recipe_app.model.dto.LoginDto;
import uz.pdp.food_recipe_app.model.dto.RegisterDto;
import uz.pdp.food_recipe_app.model.entity.User;

public interface AuthService {
    User login(LoginDto loginDto);
    void register(RegisterDto registerDto);
}
