package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

@Value
public class NewFoodsListDto {

    String name;

    Long foodPhotoId;

    Short cookingTime;

    Long userPhotoId;

    String userName;

    Float rating;

}
