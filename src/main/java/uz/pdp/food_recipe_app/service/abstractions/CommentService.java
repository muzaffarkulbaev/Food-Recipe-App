package uz.pdp.food_recipe_app.service.abstractions;

import uz.pdp.food_recipe_app.model.dto.request.CommentRequestDto;
import uz.pdp.food_recipe_app.model.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> getCommentsByFoodId(Long foodId);
    CommentResponseDto addCommentToFood(/*Long foodId,*/ CommentRequestDto dto/*, Long userId*/);
}
