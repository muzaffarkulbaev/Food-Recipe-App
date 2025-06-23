package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteFood extends BaseEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Food food;
}
