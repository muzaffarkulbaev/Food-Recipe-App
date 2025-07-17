package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

@Value
public class CommentRequestDto {

    Long foodId;

    String text;

    Long userId;

    Long commentId;

}
