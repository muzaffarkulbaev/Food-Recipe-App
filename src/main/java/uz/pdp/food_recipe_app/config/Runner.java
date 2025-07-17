package uz.pdp.food_recipe_app.config;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.food_recipe_app.model.entity.Role;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repo.RoleRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {


        if (roleRepository.findAll().isEmpty()) {
            Role roleChef = new Role("ROLE_CHEF");
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleChef);
            User admin = new User();
            admin.setName("Muzaffar");
            admin.setPassword(passwordEncoder.encode("123"));
            admin.setEmail("muzaffar@gmail.com");
            admin.setBio("I am admin also chef");
            admin.setRoles(List.of(roleAdmin));
            userRepository.save(admin);
        }
    }
}
