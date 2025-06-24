package uz.pdp.food_recipe_app.controller.authcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.food_recipe_app.model.dto.LoginDto;
import uz.pdp.food_recipe_app.model.dto.RegisterDto;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        User user = authService.login(loginDto);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            authService.register(registerDto);
            return ResponseEntity.ok("User successfully registered");
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }
}