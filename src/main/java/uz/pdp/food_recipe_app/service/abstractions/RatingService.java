package uz.pdp.food_recipe_app.service.abstractions;

import uz.pdp.food_recipe_app.model.dto.request.RatingDto;
import uz.pdp.food_recipe_app.model.entity.Rating;

public interface RatingService {
    public void addRating(RatingDto ratingDto);
    public void ratingCount(RatingDto ratingDto);
    public void updateExistRating(RatingDto ratingDto, Rating existingRating);
}
