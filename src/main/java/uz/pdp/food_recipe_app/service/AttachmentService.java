package uz.pdp.food_recipe_app.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface AttachmentService {

    Long upload(MultipartFile file);

    void get(Long attachmentId, HttpServletResponse response);
}
