package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.food_recipe_app.model.entity.Procedure;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    List<Procedure> getAllProceduresByFoodId(Long foodId);
}
