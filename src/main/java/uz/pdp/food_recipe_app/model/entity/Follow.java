package uz.pdp.food_recipe_app.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @Table(name = "follows")
    public class Follow extends BaseEntity {

        @ManyToOne
        private User follower;

        @ManyToOne
        private User chef;
    }
