package uz.pdp.food_recipe_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.food_recipe_app.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
