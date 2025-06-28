package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.FavouriteFoodDto;
import uz.pdp.food_recipe_app.model.entity.FavouriteFood;
import uz.pdp.food_recipe_app.repo.FavouriteFoodRepository;
import uz.pdp.food_recipe_app.service.abstractions.FavouriteFoodService;

@Service
@RequiredArgsConstructor
public class FavouriteFoodImpl implements FavouriteFoodService {
    private final FavouriteFoodRepository favouriteFoodRepository;


    @Override
    public void favouriteFood(FavouriteFoodDto favouriteFoodDto) {
        FavouriteFood favouriteFood = FavouriteFood.builder()
                .userId(favouriteFoodDto.getUserId())
                .foodId(favouriteFoodDto.getFoodId())
                .build();
        favouriteFoodRepository.save(favouriteFood);
    }

    @Override
    public void deleteFavouriteFood(FavouriteFoodDto favouriteFoodDto) {
        FavouriteFood favouriteFood = favouriteFoodRepository.findByFoodId(favouriteFoodDto.getFoodId());
        favouriteFoodRepository.delete(favouriteFood);
    }
}
