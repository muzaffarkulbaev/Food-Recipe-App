package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.food_recipe_app.model.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long>{

    List<Comment> findByFoodId(Long foodId);
}
