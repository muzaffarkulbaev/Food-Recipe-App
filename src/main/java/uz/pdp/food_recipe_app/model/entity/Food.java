package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Food extends BaseEntity {

    private String name;

    private String description;

    private Short cookingTime;

    private Float rating;

    @ManyToOne
    private Attachment attachment;

    @ManyToOne
    private Category category;

    @ManyToOne
    User user;

}
