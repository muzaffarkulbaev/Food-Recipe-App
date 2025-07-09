package uz.pdp.food_recipe_app.service.abstractions;


import uz.pdp.food_recipe_app.model.entity.User;

import java.util.List;

public interface FollowService {

    Integer findFollowersById(Long userId);

    Integer findFollowingsById(Long userId);

    void toggleFollow(Long followerId, Long userId);

    List<User> findFollowers(Long userId);

}
