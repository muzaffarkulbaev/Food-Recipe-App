package uz.pdp.food_recipe_app.service.abstractions;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_recipe_app.model.dto.request.LoginDto;
import uz.pdp.food_recipe_app.model.dto.request.RegisterDto;
import uz.pdp.food_recipe_app.model.dto.response.LoginResponse;
import uz.pdp.food_recipe_app.model.entity.User;

public interface AuthService {

    LoginResponse login(LoginDto loginDto);

    void register(RegisterDto registerDto);

}
