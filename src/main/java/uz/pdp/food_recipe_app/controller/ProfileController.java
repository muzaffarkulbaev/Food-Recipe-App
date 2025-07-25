package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.food_recipe_app.model.dto.response.UserProfileDto;
import uz.pdp.food_recipe_app.model.dto.response.UserProfileRecipeDto;
import uz.pdp.food_recipe_app.service.abstractions.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/account/{id}")
    public ResponseEntity<UserProfileDto> getUserProfileDataById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserProfileData(id), HttpStatus.OK);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<List<UserProfileRecipeDto>> getUserProfileRecipesDataById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserProfileRecipesDataById(id), HttpStatus.OK);
    }
}