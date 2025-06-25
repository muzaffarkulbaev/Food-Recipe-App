package uz.pdp.food_recipe_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.food_recipe_app.model.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}

