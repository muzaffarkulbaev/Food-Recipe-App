package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.CommentRequestDto;
import uz.pdp.food_recipe_app.model.dto.response.CommentResponseDto;
import uz.pdp.food_recipe_app.model.entity.Comment;
import uz.pdp.food_recipe_app.model.entity.Food;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repo.CommentReactionRepository;
import uz.pdp.food_recipe_app.repo.CommentRepository;
import uz.pdp.food_recipe_app.repo.FoodRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;
    private final CommentReactionRepository commentReactionRepository;

    @Override
    public List<CommentResponseDto> getCommentsByFoodId(Long foodId) {
        List<Comment> comments = commentRepository.findByFoodId(foodId);
        return comments.stream().map(comment -> new CommentResponseDto(
                        comment.getUser().getName(),
                        comment.getText(),
                        comment.getCreatedAt(),
                        comment.getUser().getAttachment().getUrl(),
                        commentReactionRepository.getCommentReactionByCommentIdLike(comment.getId()),
                        commentReactionRepository.getCommentReactionByCommentIdDislike(comment.getId())
                ))
                .toList();
    }

    @Override
    public CommentResponseDto addCommentToFood(/*Long foodId,*/ CommentRequestDto dto/*, Long userId*/) {
        Food food = foodRepository.findById(dto.getFoodId())
                .orElseThrow(() -> new RuntimeException("Food not found"));

        User user = userRepository.findById(dto.getUserId()).orElseThrow();

        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setUser(user);
        comment.setFood(food);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);

        return new CommentResponseDto(
                user.getName(),
                comment.getText(),
                comment.getCreatedAt(),
                user.getAttachment().getUrl() != null ? user.getAttachment().getUrl() : null,
                0,
                0
//                comment.getCommentReaction().getLikeAmount(),
//                comment.getCommentReaction().getDislikeAmount()
        );
    }
}
