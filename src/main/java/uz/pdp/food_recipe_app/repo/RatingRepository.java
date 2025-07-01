package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.food_recipe_app.model.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findRatingByUserIdAndFoodId(Long userId, Long foodId);
}
