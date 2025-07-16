package uz.pdp.food_recipe_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_recipe_app.model.dto.CategoryDto;
import uz.pdp.food_recipe_app.model.dto.response.CategoryResponseDto;
import uz.pdp.food_recipe_app.model.entity.Category;
import uz.pdp.food_recipe_app.repo.CategoryRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        categoryRepository.save(new Category(categoryDto.getName()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryRepository.findAll().stream().map( category -> new CategoryResponseDto(category.getName(), category.getId())).toList());
    }

}
