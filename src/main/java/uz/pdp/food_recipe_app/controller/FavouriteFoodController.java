package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.food_recipe_app.model.dto.request.FavouriteFoodDto;
import uz.pdp.food_recipe_app.service.abstractions.FavouriteFoodService;

@RestController
@RequestMapping("/api/favourite")
@RequiredArgsConstructor
public class FavouriteFoodController {

    private final FavouriteFoodService favouriteFoodService;


    @PostMapping("/add")
    public ResponseEntity<?> favouriteFood(@RequestBody FavouriteFoodDto favouriteFoodDto) {
        try {
            favouriteFoodService.favouriteFood(favouriteFoodDto);
            return ResponseEntity.ok("Food successfully added to the favourite list");
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteFavouriteFood(@RequestBody FavouriteFoodDto favouriteFoodDto) {
        try {
            favouriteFoodService.deleteFavouriteFood(favouriteFoodDto);
            return ResponseEntity.ok("Food successfully deleted from the favourite list");
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }
}
