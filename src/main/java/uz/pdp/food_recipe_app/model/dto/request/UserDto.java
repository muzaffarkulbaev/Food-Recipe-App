package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

@Value
public class UserDto {
    Long id;
    String name;
    String email;
    String urlImage;
}
