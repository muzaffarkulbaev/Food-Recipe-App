package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class NotificationDto {

    String message;
    String senderName;
    LocalDateTime createdTime;

}
