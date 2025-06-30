package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class FavouriteFoodListDto {

    String foodName;

    String chefName;

    Short prepareTime;

    Float rating;

}
