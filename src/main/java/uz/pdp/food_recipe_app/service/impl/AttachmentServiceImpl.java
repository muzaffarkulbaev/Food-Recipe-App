package uz.pdp.food_recipe_app.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_recipe_app.model.entity.Attachment;
import uz.pdp.food_recipe_app.repo.AttachmentRepository;
import uz.pdp.food_recipe_app.service.AttachmentService;
import uz.pdp.food_recipe_app.service.S3Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final S3Service s3Service;
    private final AttachmentRepository attachmentRepository;

    @Override
    public UUID upload(MultipartFile file) {
        Attachment attachment = new Attachment();
        String key = s3Service.uploadImage(file);
        attachment.setUrl(key);
        Attachment saved = attachmentRepository.save(attachment);
        return saved.getId();
    }

    @SneakyThrows
    @Override
    public void get(UUID attachmentId, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow();
        byte[] image = s3Service.getImage(attachment.getUrl());
        response.getOutputStream().write(image);
    }
}
