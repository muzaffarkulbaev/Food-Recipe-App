package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class UserProfileDto {
    String name;
    Long photoId;
    String bio;
    Integer followers;
    Integer following;
    Integer foodAmount;
}
