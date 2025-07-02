package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.food_recipe_app.model.entity.Food;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findById(Long id);

    List<Food> findByCategoryId(Long categoryId);

    List<Food> findByUserId(Long userId);

    List<Food> findAllByUserId(Long userId);

    List<Food> findByIdIn(List<Long> ids);

    @Query("SELECT f FROM Food f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Food> findByNameLike(@Param("keyword") String keyword);

}

