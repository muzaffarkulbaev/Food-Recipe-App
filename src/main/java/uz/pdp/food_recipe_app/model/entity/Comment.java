package uz.pdp.food_recipe_app.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.food_recipe_app.model.base.BaseEntity;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends BaseEntity {

    @ManyToOne
    private Food food;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Boolean isActive = true;

//    @OneToOne(mappedBy = "comment", cascade = CascadeType.ALL)
//    private CommentReaction commentReaction;

}
