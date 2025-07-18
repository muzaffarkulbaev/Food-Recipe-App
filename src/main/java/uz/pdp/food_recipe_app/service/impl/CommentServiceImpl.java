package uz.pdp.food_recipe_app.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.CommentDeletingDto;
import uz.pdp.food_recipe_app.model.dto.request.CommentRequestDto;
import uz.pdp.food_recipe_app.model.dto.request.ReactionDto;
import uz.pdp.food_recipe_app.model.dto.response.CommentResponseDto;
import uz.pdp.food_recipe_app.model.entity.Comment;
import uz.pdp.food_recipe_app.model.entity.CommentReaction;
import uz.pdp.food_recipe_app.model.entity.Food;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.model.enums.ReactionStatus;
import uz.pdp.food_recipe_app.repo.CommentReactionRepository;
import uz.pdp.food_recipe_app.repo.CommentRepository;
import uz.pdp.food_recipe_app.repo.FoodRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Transactional
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
        return comments.stream()
                .filter(Comment::getIsActive)
                .map(comment -> new CommentResponseDto(
                        comment.getId(),
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
    public CommentResponseDto addCommentToFood(CommentRequestDto dto) {
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
                commentReactionRepository.getCommentReactionByCommentIdLike(comment.getId()),
                commentReactionRepository.getCommentReactionByCommentIdDislike(comment.getId())
        );
    }

    @Override
    public String reactionProcess(ReactionDto reactionDto) {
        String reaction = reactionDto.getReaction();
        return switch (reaction) {
            case "LIKE" -> reactionLike(reactionDto);
            case "DISLIKE" -> reactionDislike(reactionDto);
            default -> "Reaction status is invalid";
        };
    }

    @Override
    public CommentResponseDto updateComment(CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(requestDto.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(requestDto.getUserId())) {
            throw new RuntimeException("You are not allowed to update this comment");
        }

        comment.setText(requestDto.getText());
        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                comment.getUser().getName(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getUser().getAttachment().getUrl(),
                commentReactionRepository.getCommentReactionByCommentIdLike(comment.getId()),
                commentReactionRepository.getCommentReactionByCommentIdDislike(comment.getId())
        );
    }

    public CommentDeletingDto deleteComment(CommentDeletingDto requestDto) {
        Comment comment = commentRepository.findById(requestDto.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        if (!comment.getUser().getId().equals(requestDto.getUserId())) {
            throw new RuntimeException("You are not allowed to DELETE this comment");
        }
        comment.setIsActive(false);
        commentRepository.save(comment);
        return requestDto;
    }


    private String reactionLike(ReactionDto reactionDto) {
        CommentReaction commentReaction = commentReactionRepository.findByCommentIdAndUserId(reactionDto.getCommentId(), reactionDto.getUserId());
        if (commentReaction == null) {
            CommentReaction newCommentReaction = new CommentReaction();
            newCommentReaction.setUser(userRepository.findById(reactionDto.getUserId()).orElseThrow());
            newCommentReaction.setComment(commentRepository.findById(reactionDto.getCommentId()).orElseThrow());
            newCommentReaction.setReactionStatus(ReactionStatus.LIKE);
            commentReactionRepository.save(newCommentReaction);
            return "Like is clicked";
        }
        if (Objects.equals(commentReaction.getReactionStatus(), ReactionStatus.LIKE)) {
            commentReactionRepository.delete(commentReaction);
            return "Like is deleted";
        } else {
            commentReaction.setReactionStatus(ReactionStatus.LIKE);
            return "Dislike changed to like";
        }
    }

    private String reactionDislike(ReactionDto reactionDto) {
        CommentReaction commentReaction = commentReactionRepository.findByCommentIdAndUserId(reactionDto.getCommentId(), reactionDto.getUserId());

        if (commentReaction == null) {
            CommentReaction newCommentReaction = new CommentReaction();
            newCommentReaction.setUser(userRepository.findById(reactionDto.getUserId()).orElseThrow());
            newCommentReaction.setComment(commentRepository.findById(reactionDto.getCommentId()).orElseThrow());
            newCommentReaction.setReactionStatus(ReactionStatus.DISLIKE);
            commentReactionRepository.save(newCommentReaction);
            return "Dislike is clicked";
        }

        if (Objects.equals(commentReaction.getReactionStatus(), ReactionStatus.DISLIKE)) {
            commentReactionRepository.delete(commentReaction);
            return "Dislike is deleted";
        } else {
            commentReaction.setReactionStatus(ReactionStatus.DISLIKE);
            return "Like changed to dislike";
        }
    }
}
