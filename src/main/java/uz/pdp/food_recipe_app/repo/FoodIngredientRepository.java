package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.food_recipe_app.model.entity.FoodIngredient;


public interface FoodIngredientRepository extends JpaRepository<FoodIngredient, Long> {
}
