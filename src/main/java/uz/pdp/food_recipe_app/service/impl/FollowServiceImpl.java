package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.entity.Follow;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repo.FollowRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.FollowService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public Integer findFollowersById(Long userId) {
        return followRepository.countFollowersByChefId(userId);
    }

    @Override
    public Integer findFollowingsById(Long userId) {
        return followRepository.countFollowingByFollowerId(userId);
    }

    @Override
    public void toggleFollow(Long followerId, Long userId) {
        if (followerId.equals(userId)) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }

        Optional<Follow> followOptional = followRepository.findByFollowerIdAndUserId(followerId, userId);

        if (followOptional.isPresent()) {
            followRepository.delete(followOptional.get()); // Unfollow
        } else {
            Follow follow = new Follow();
            follow.setFollower(userRepository.findById(followerId).orElseThrow());
            follow.setUser(userRepository.findById(userId).orElseThrow());
            followRepository.save(follow);
        }

    }

    @Override
    public List<User> findFollowers(Long userId) {
        return followRepository.findFollowersByUserId(userId);
    }

}
