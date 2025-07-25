package uz.pdp.food_recipe_app.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_recipe_app.model.entity.Attachment;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repo.AttachmentRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.AttachmentService;
import uz.pdp.food_recipe_app.service.abstractions.S3Service;

@Transactional
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final S3Service s3Service;
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;

    @Override
    public Long upload(MultipartFile file) {
        Attachment attachment = new Attachment();
        String key = s3Service.uploadImage(file);
        attachment.setUrl(key);
        Attachment saved = attachmentRepository.save(attachment);
        return saved.getId();
    }

    @SneakyThrows
    @Override
    public void get(Long attachmentId, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow();
        byte[] image = s3Service.getImage(attachment.getUrl());
        response.getOutputStream().write(image);
    }

    @Override
    public void setAttachmentToUser(Long userId, Long attachmentId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setAttachment(attachmentRepository.findById(attachmentId).orElseThrow());
        userRepository.save(user);
    }

}
