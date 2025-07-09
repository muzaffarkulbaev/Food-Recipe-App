package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Role extends BaseEntity implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
