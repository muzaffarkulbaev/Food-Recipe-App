package uz.pdp.food_recipe_app.service.abstractions;

import uz.pdp.food_recipe_app.model.dto.request.FoodAddDto;
import uz.pdp.food_recipe_app.model.dto.response.NotificationDto;

import java.util.List;

public interface NotificationService {
    List<NotificationDto> getAllNotifications(Long userId);
    List<NotificationDto> getReadNotifications(Long userId);
    List<NotificationDto> getUnReadNotifications(Long userId);
    void createAndSendToUsers(FoodAddDto foodAddDto);
}
