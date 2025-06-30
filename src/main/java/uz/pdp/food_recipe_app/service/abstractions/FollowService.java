package uz.pdp.food_recipe_app.service.abstractions;


public interface FollowService {

    Integer findFollowersById(Long userId);

    Integer findFollowingsById(Long userId);

    void toggleFollow(Long followerId, Long userId);

}
