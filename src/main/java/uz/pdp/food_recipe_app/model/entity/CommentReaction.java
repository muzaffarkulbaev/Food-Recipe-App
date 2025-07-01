package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.food_recipe_app.model.base.BaseEntity;
import uz.pdp.food_recipe_app.model.enums.ReactionStatus;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment_reactions")
public class CommentReaction extends BaseEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;

    @Enumerated(value = EnumType.STRING)
    private ReactionStatus reactionStatus;
}
