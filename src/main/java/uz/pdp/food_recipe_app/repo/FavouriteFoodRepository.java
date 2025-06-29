package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.food_recipe_app.model.dto.request.FavouriteFoodDto;
import uz.pdp.food_recipe_app.model.entity.FavouriteFood;

import java.util.List;

@Repository
public interface FavouriteFoodRepository extends JpaRepository<FavouriteFood, Long> {
    FavouriteFood findByFoodId(Long foodId);

    List<FavouriteFoodDto> findByUserId(Long userId);

    @Query("SELECT f.foodId FROM FavouriteFood f WHERE f.userId = :userId")
    List<Long> getFoodIdsByUserId(Long userId);

}
