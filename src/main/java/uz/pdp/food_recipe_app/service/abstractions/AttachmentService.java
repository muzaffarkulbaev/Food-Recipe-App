package uz.pdp.food_recipe_app.service.abstractions;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

    Long upload(MultipartFile file);

    void get(Long attachmentId, HttpServletResponse response);
}
