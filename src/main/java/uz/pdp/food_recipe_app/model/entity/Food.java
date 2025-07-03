package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;
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
    private Short cookingTime;
    private Float rating;
    private Integer viewAmount;

    @ManyToOne
    private Attachment attachment;

    @ManyToOne
    private Category category;

    @ManyToOne
    User user;

}
