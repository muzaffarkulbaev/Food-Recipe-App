package uz.pdp.food_recipe_app.service.abstractions;

import uz.pdp.food_recipe_app.model.dto.request.FavouriteFoodDto;

public interface FavouriteFoodService {
    void favouriteFood(FavouriteFoodDto favouriteFoodDto);
    void deleteFavouriteFood(FavouriteFoodDto favouriteFoodDto);
}
