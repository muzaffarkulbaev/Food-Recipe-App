package uz.pdp.food_recipe_app.controller.dashcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.food_recipe_app.service.FoodService;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final FoodService recipeService;

//    @GetMapping
//    public ResponseEntity<?> getDashboardData() {
//        return ResponseEntity.ok(dashboard);
//    }
}
