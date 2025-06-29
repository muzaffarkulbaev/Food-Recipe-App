package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Ingredient extends BaseEntity {

    private String name;

    @ManyToOne
    private Attachment attachment;

}
