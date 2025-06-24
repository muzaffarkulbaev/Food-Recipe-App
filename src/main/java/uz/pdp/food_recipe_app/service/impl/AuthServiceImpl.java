package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.LoginDto;
import uz.pdp.food_recipe_app.model.dto.RegisterDto;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.model.enums.Role;
import uz.pdp.food_recipe_app.repositories.UserRepository;
import uz.pdp.food_recipe_app.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null) return null;
        if (!user.getPassword().equals(loginDto.getPassword())) return null;
        return user;
    }

    @SneakyThrows
    @Override
    public void register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }
        User user = User.builder()
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .name(registerDto.getName())
                .role(Role.CHEF)
                .build();
        userRepository.save(user);
    }

}
