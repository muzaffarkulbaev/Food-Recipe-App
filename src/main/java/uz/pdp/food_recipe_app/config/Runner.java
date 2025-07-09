package uz.pdp.food_recipe_app.config;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.food_recipe_app.model.entity.Role;
import uz.pdp.food_recipe_app.repo.RoleRepository;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {


        if (roleRepository.findAll().isEmpty()) {
            Role roleChef = new Role("ROLE_CHEF");
            roleRepository.save(roleChef);
        }
    }
}
