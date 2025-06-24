package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.UserDto;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repositories.UserRepository;
import uz.pdp.food_recipe_app.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImlp implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAttachment().getUrl()
        );
    }
}
