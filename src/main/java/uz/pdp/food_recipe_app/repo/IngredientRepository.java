package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.food_recipe_app.model.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
