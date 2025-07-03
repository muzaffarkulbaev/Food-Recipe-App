package uz.pdp.food_recipe_app.model.dto.request;

import lombok.Value;

import java.util.List;

@Value
public class FoodWithProcedureDto {
     FoodAddDto foodAddDto;
     List<String> proceduresList;
}
