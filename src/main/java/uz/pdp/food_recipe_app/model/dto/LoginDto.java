package uz.pdp.food_recipe_app.model.dto;

import lombok.Value;

@Value
public class LoginDto {
    String email;
    String password;
}
