package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_recipe_app.model.dto.request.FilterDto;
import uz.pdp.food_recipe_app.model.dto.request.FoodAddDto;
import uz.pdp.food_recipe_app.model.dto.request.FoodWithProcedureDto;
import uz.pdp.food_recipe_app.model.dto.response.FoodByCategoryDto;
import uz.pdp.food_recipe_app.model.dto.response.FoodResponceDto;
import uz.pdp.food_recipe_app.model.dto.response.NewFoodsListDto;
import uz.pdp.food_recipe_app.service.abstractions.FoodService;
import uz.pdp.food_recipe_app.service.abstractions.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;
    private final NotificationService notificationService;

    @GetMapping("/new")
    public ResponseEntity<List<NewFoodsListDto>> getNewFoods() {
       return new ResponseEntity<>(foodService.getNewFoods(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodResponceDto>> getAllFoods() {
        return new ResponseEntity<>(foodService.getAllFoods(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<FoodResponceDto>> getFoodByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(foodService.getFoodsByCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewFood(@RequestBody FoodWithProcedureDto foodWithProcedureDto) {
        try {
            foodService.addNewFood(foodWithProcedureDto.getFoodAddDto(), foodWithProcedureDto.getProceduresList());
            FoodAddDto foodAddDto = foodWithProcedureDto.getFoodAddDto();
            notificationService.createAndSendToUsers(foodAddDto);
            return ResponseEntity.ok("Food successfully added");
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @GetMapping("procedure/{foodId}")
    public ResponseEntity<?> getFoodByProcedure(@PathVariable Long foodId) {
        return ResponseEntity.ok(foodService.getFoodProcedures(foodId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodResponceDto>> getSearchedFoods(@RequestParam String search) {
        return ResponseEntity.ok(foodService.getSearchedFoods(search));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<FoodResponceDto>> getFoodsByFilter(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(foodService.getFoodsByFilter(filterDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FoodResponceDto>> getFoodsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(foodService.getFoodsByUserId(userId));
    }

}
