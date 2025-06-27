package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.repo.FollowRepository;
import uz.pdp.food_recipe_app.service.abstractions.FollowService;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;

    @Override
    public Integer findFollowersById(Long userId) {
        return followRepository.countFollowersByChefId(userId);
    }

    @Override
    public Integer findFollowingsById(Long userId) {
        return followRepository.countFollowingByFollowerId(userId);
    }
}
