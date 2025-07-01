package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

@Value
public class RatingDto {
    Long foodId;
    Long userId;
    Float rating;
}
