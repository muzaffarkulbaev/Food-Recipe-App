package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uz.pdp.food_recipe_app.model.base.BaseEntity;
import uz.pdp.food_recipe_app.model.enums.Role;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String bio;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Attachment attachment;

    @Enumerated(EnumType.STRING)
    private Role role;

}
