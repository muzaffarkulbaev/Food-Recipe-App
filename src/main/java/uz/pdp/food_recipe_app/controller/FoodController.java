package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_recipe_app.model.dto.request.FavouriteFoodDto;
import uz.pdp.food_recipe_app.model.dto.request.FoodAddDto;
import uz.pdp.food_recipe_app.model.dto.response.FoodByCategoryDto;
import uz.pdp.food_recipe_app.model.dto.response.NewFoodsListDto;
import uz.pdp.food_recipe_app.service.abstractions.FavouriteFoodService;
import uz.pdp.food_recipe_app.service.abstractions.FoodService;


import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;


    @GetMapping("/new")
    public ResponseEntity<List<NewFoodsListDto>> getNewFoods() {
       return new ResponseEntity<>(foodService.getNewFoods(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodByCategoryDto>> getAllFoods() {
        return new ResponseEntity<>(foodService.getAllFoods(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<FoodByCategoryDto>> getFoodByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(foodService.getFoodsByCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewFood(@RequestBody FoodAddDto foodAddDto) {
        try {
            foodService.addNewFood(foodAddDto);
            return ResponseEntity.ok("Food successfully added");
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

}
