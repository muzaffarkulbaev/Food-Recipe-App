package uz.pdp.food_recipe_app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.food_recipe_app.model.dto.request.CommentRequestDto;
import uz.pdp.food_recipe_app.model.dto.request.ReactionDto;
import uz.pdp.food_recipe_app.model.dto.response.CommentResponseDto;
import uz.pdp.food_recipe_app.service.abstractions.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/food/{foodId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByFood(@PathVariable Long foodId) {
        List<CommentResponseDto> comments = commentService.getCommentsByFoodId(foodId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/add")
    public ResponseEntity<CommentResponseDto> addCommentToFood(@RequestBody CommentRequestDto requestDto){
        CommentResponseDto comment = commentService.addCommentToFood(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/update")
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentRequestDto requestDto){
        CommentResponseDto updatedComment = commentService.updateComment(requestDto);
        return ResponseEntity.ok(updatedComment);
    }

    @PostMapping("/reaction")
    public ResponseEntity<String> reactionProcess(@RequestBody ReactionDto reactionDto){
        String message = commentService.reactionProcess(reactionDto);
        return ResponseEntity.ok(message);
    }
}


