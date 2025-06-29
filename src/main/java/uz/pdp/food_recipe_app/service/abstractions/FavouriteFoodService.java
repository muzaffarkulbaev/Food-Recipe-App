package uz.pdp.food_recipe_app.service.abstractions;

import uz.pdp.food_recipe_app.model.dto.request.FavouriteFoodDto;
import uz.pdp.food_recipe_app.model.dto.response.FavouriteFoodListDto;

import java.util.List;

public interface FavouriteFoodService {
    void favouriteFood(FavouriteFoodDto favouriteFoodDto);
    void deleteFavouriteFood(FavouriteFoodDto favouriteFoodDto);
    List<FavouriteFoodListDto> getAllFavouriteFood(Long userId);
}
