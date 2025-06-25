package uz.pdp.food_recipe_app.controller.home;

import lombok.RequiredArgsConstructor;
import uz.pdp.food_recipe_app.dtos.HomeDTO;
import uz.pdp.food_recipe_app.service.abstractions.EventService;
import uz.pdp.food_recipe_app.service.abstractions.ReviewService;
import uz.pdp.food_recipe_app.service.abstractions.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

    private final EventService eventService;
    private final UserService userService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<HomeDTO> getHomeData() {
        HomeDTO homeResponse = new HomeDTO();
        homeResponse.setLatestEvents(eventService.getLatestEvents());
        homeResponse.setTopOrganizers(userService.getTopOrganizers());
        homeResponse.setRecentReviews(reviewService.getRecentReviews());
        return ResponseEntity.ok(homeResponse);
    }
}
