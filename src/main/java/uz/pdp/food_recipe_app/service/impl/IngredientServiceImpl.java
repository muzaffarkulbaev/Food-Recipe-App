package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.food_recipe_app.model.dto.request.IngredientNewDto;
import uz.pdp.food_recipe_app.model.entity.Attachment;
import uz.pdp.food_recipe_app.model.entity.Ingredient;
import uz.pdp.food_recipe_app.repo.AttachmentRepository;
import uz.pdp.food_recipe_app.repo.IngredientRepository;
import uz.pdp.food_recipe_app.service.abstractions.IngredientService;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public void addIngredient(IngredientNewDto ingredient) {
        Ingredient foundIngredient = ingredientRepository.findByName(ingredient.getName());
        if (foundIngredient != null) throw new RuntimeException("Ingredient already exists");
        Ingredient newIngredient = new Ingredient();
        newIngredient.setName(ingredient.getName());
        Attachment attachment = attachmentRepository.findById(ingredient.getPhotoId()).orElseThrow(() -> new RuntimeException("Attachment not found"));
        newIngredient.setAttachment(attachment);
        ingredientRepository.save(newIngredient);
    }



}
