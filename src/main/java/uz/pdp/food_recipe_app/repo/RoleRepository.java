package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.food_recipe_app.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String roleChef);
}