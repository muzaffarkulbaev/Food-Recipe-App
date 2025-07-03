package uz.pdp.food_recipe_app.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.dto.request.RatingDto;
import uz.pdp.food_recipe_app.model.entity.Food;
import uz.pdp.food_recipe_app.model.entity.Rating;
import uz.pdp.food_recipe_app.model.entity.RatingCounter;
import uz.pdp.food_recipe_app.repo.FoodRepository;
import uz.pdp.food_recipe_app.repo.RatingCounterRepository;
import uz.pdp.food_recipe_app.repo.RatingRepository;
import uz.pdp.food_recipe_app.service.abstractions.RatingService;

@Transactional
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingCounterRepository ratingCounterRepository;
    private final FoodRepository foodRepository;

    @Override
    public void addRating(RatingDto ratingDto) {

        Rating existRating = ratingRepository.findRatingByUserIdAndFoodId(ratingDto.getUserId(),ratingDto.getFoodId());
        if(existRating != null) {
            updateExistRating(ratingDto,existRating);
        }else {
            Rating rating = Rating.builder()
                    .userId(ratingDto.getUserId())
                    .foodId(ratingDto.getFoodId())
                    .rating(ratingDto.getRating())
                    .build();

            ratingRepository.save(rating);
            ratingCount(ratingDto);
        }
    }

    @Override
    public void ratingCount(RatingDto ratingDto) {

        RatingCounter ratingCounter = ratingCounterRepository.findByFoodId(ratingDto.getFoodId());
        if(ratingCounter != null) {

            Float avgR = ratingCounter.getAverageRating();
            Float rA = ratingCounter.getRatingAmount();
            Float newAvgRating = (avgR * rA + ratingDto.getRating()) / (rA+1F);

            ratingCounter.setAverageRating(newAvgRating);
            ratingCounter.setRatingAmount(rA+1F);

            Food food = foodRepository.findById(ratingDto.getFoodId()).orElse(null);
            assert food != null;
            food.setRating(newAvgRating);

        }else{
            ratingCounter = new RatingCounter();
            ratingCounter.setAverageRating(ratingDto.getRating());
            ratingCounter.setRatingAmount(1F);
            ratingCounter.setFoodId(ratingDto.getFoodId());

            ratingCounterRepository.save(ratingCounter);

            Food food = foodRepository.findById(ratingDto.getFoodId()).orElse(null);
            assert food != null;
            food.setRating(ratingDto.getRating());
        }
    }
    @Override
    public void updateExistRating(RatingDto ratingDto, Rating existRating) {
        RatingCounter ratingCounter = ratingCounterRepository.findByFoodId(ratingDto.getFoodId());
        Float avgR = ratingCounter.getAverageRating();
        Float rA = ratingCounter.getRatingAmount();
        Float newAvgRating = (avgR * rA - existRating.getRating() + ratingDto.getRating()) / rA;
        ratingCounter.setAverageRating(newAvgRating);
        existRating.setRating(ratingDto.getRating());

        Food food = foodRepository.findById(ratingDto.getFoodId()).orElse(null);
        assert food != null;
        food.setRating(newAvgRating);
    }
}
