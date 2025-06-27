package uz.pdp.food_recipe_app.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_recipe_app.service.abstractions.AttachmentService;


@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping
    public Long uploadFile(@RequestParam("file") MultipartFile file) {
        return attachmentService.upload(file);
    }

    @GetMapping("/{attachmentId}")
    public void getFile(@PathVariable Long attachmentId, HttpServletResponse response) {
        attachmentService.get(attachmentId, response);
    }

}
