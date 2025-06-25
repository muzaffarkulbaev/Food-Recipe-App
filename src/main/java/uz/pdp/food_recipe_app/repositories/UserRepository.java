package uz.pdp.food_recipe_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.food_recipe_app.model.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
