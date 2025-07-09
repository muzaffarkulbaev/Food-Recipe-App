package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.food_recipe_app.model.entity.Follow;
import uz.pdp.food_recipe_app.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.user.id = :userId")
    Integer countFollowersByChefId(@Param("userId") Long userId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :userId")
    Integer countFollowingByFollowerId(@Param("userId") Long userId);

    Optional<Follow> findByFollowerIdAndUserId(Long followerId, Long userId);

    @Query("SELECT f.follower FROM Follow f WHERE f.user.id = :userId")
    List<User> findFollowersByUserId(@Param("userId") Long userId);

}
