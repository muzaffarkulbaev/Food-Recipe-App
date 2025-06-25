package uz.pdp.food_recipe_app.model.dto;


import lombok.Value;

@Value
public class RegisterDto {

    String name;
    String email;
    String password;
    String confirmPassword;

}


