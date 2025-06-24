package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

    private String name;

}
