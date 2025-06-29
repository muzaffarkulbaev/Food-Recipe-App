package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_recipe_app.service.abstractions.FollowService;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{id}")
    public ResponseEntity<String> followOrUnfollow(@PathVariable Long id,
                                                   @RequestParam Long followerId) {
        followService.toggleFollow(followerId, id);
        return ResponseEntity.ok("Follow state changed.");
    }
}

