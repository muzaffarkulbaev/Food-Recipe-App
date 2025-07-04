package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.food_recipe_app.model.dto.response.NotificationDto;
import uz.pdp.food_recipe_app.service.abstractions.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<NotificationDto>> getAllNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getAllNotifications(userId));
    }

    @GetMapping("/read/{userId}")
    public ResponseEntity<List<NotificationDto>> getReadNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getReadNotifications(userId));
    }


    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<NotificationDto>> getUnReadNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUnReadNotifications(userId));
    }

}
