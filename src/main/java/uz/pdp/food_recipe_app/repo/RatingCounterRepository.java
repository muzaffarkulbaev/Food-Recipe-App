package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.food_recipe_app.model.entity.RatingCounter;

@Repository
public interface RatingCounterRepository extends JpaRepository<RatingCounter, Long> {
    RatingCounter findByFoodId(Long foodId);

}


