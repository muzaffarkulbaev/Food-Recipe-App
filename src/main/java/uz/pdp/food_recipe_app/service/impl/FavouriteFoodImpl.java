package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.FavouriteFoodDto;
import uz.pdp.food_recipe_app.model.dto.response.FavouriteFoodListDto;
import uz.pdp.food_recipe_app.model.entity.FavouriteFood;
import uz.pdp.food_recipe_app.model.entity.Food;
import uz.pdp.food_recipe_app.repo.FavouriteFoodRepository;
import uz.pdp.food_recipe_app.repo.FoodRepository;
import uz.pdp.food_recipe_app.service.abstractions.FavouriteFoodService;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class FavouriteFoodImpl implements FavouriteFoodService {
    private final FavouriteFoodRepository favouriteFoodRepository;
    private final FoodRepository foodRepository;


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

    @Override
    public List<FavouriteFoodListDto> getAllFavouriteFood(Long userId) {
        List<Long> foodIds = favouriteFoodRepository.getFoodIdsByUserId(userId);
        List<Food> favFoodsList = foodRepository.findByIdIn(foodIds);

        return favFoodsList.stream()
                .map(favFood -> new FavouriteFoodListDto(
                        favFood.getName(),
                        favFood.getUser().getName(),
                        favFood.getCookingTime(),
                        favFood.getRating()
                ))
                .toList();
    }

}
