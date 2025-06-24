package uz.pdp.food_recipe_app.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface AttachmentService {

    UUID upload(MultipartFile file);

    void get(UUID attachmentId, HttpServletResponse response);
}
