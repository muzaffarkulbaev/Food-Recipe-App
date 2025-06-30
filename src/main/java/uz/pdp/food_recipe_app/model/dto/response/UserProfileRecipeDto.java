package uz.pdp.food_recipe_app.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.food_recipe_app.model.entity.Food;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProfileRecipeDto {

    private Long id;
    private String name;
    private Short cookingTime;
    private Long attachmentId;
    private String ownerName;
    private Float rating;

    public static List<UserProfileRecipeDto> toDTOS(List<Food> userAllFood) {
        List<UserProfileRecipeDto> foodDTOS = new ArrayList<>();
        for (Food food : userAllFood) {
            foodDTOS.add(
                    UserProfileRecipeDto.builder()
                            .id(food.getId())
                            .name(food.getName())
                            .cookingTime(food.getCookingTime())
                            .rating(food.getRating())
                            .cookingTime(food.getCookingTime())
                            .ownerName(food.getUser().getName())
                            .build());
        }
        return foodDTOS;
    }
}
