package uz.pdp.food_recipe_app.model.dto.response;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CommentResponseDto {
    Long id;

    String userName;

    String text;

    LocalDateTime date;

    String userAttachmentUrl;

    Integer likeAmount;

    Integer dislikeAmount;
}
