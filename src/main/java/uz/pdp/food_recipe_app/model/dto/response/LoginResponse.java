package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class LoginResponse {
    String token;
    Long userId;
    Boolean isAdmin;
}
