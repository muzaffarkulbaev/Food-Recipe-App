package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_recipe_app.model.dto.request.IngredientNewDto;
import uz.pdp.food_recipe_app.model.entity.Ingredient;
import uz.pdp.food_recipe_app.repo.IngredientRepository;
import uz.pdp.food_recipe_app.service.abstractions.IngredientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientRepository ingredientRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addNewIngredient(@RequestBody IngredientNewDto ingredientNewDto) {
        ingredientService.addIngredient(ingredientNewDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllIngredientNames() {
        List<String> ingredientNames = ingredientRepository.findAll()
                .stream()
                .map(Ingredient::getName)
                .toList();

        return ResponseEntity.ok(ingredientNames);
    }


}
