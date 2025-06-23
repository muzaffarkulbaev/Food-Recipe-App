package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne
    private Food food;

    @ManyToOne
    private User senderUser;

}
