package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.food_recipe_app.model.entity.FavouriteFood;

@Repository
public interface FavouriteFoodRepository extends JpaRepository<FavouriteFood, Long> {
    FavouriteFood findByFoodId(Long foodId);
}
