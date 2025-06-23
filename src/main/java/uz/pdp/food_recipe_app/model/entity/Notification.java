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
@Table(name = "notifications")
public class Notification extends BaseEntity {

    private String message;

    @ManyToOne
    private User chef;

    @ManyToOne
    private User receiverUser;

    private Boolean isRead = false;

}
