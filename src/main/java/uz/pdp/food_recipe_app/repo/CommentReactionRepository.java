package uz.pdp.food_recipe_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.food_recipe_app.model.entity.CommentReaction;

public interface CommentReactionRepository extends JpaRepository<CommentReaction,Long> {

//    Integer getCommentReactionByReactionStatus(ReactionStatus reactionStatus);

    @Query("SELECT COUNT(cr) FROM CommentReaction cr WHERE cr.comment.id = :commentId AND cr.reactionStatus = 'LIKE'")
    Integer getCommentReactionByCommentIdLike(@Param("commentId") Long commentId);

    @Query("SELECT COUNT(cr) FROM CommentReaction cr WHERE cr.comment.id = :commentId AND cr.reactionStatus = 'DISLIKE'")
    Integer getCommentReactionByCommentIdDislike(@Param("commentId") Long commentId);

    @Query("SELECT cr FROM CommentReaction cr WHERE cr.comment.id = :commentId AND cr.user.id = :userId")
    CommentReaction findByCommentIdAndUserId(@Param("commentId") Long commentId,
                                                       @Param("userId") Long userId);
}
