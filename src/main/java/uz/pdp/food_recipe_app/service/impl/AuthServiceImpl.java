package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_recipe_app.model.dto.request.LoginDto;
import uz.pdp.food_recipe_app.model.dto.request.RegisterDto;
import uz.pdp.food_recipe_app.model.entity.Attachment;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.model.enums.Role;
import uz.pdp.food_recipe_app.repo.AttachmentRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.service.abstractions.AuthService;
import uz.pdp.food_recipe_app.service.abstractions.S3Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final AttachmentRepository attachmentRepository;

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        if (user == null) return null;
        if (!user.getPassword().equals(loginDto.getPassword())) return null;
        return user;
    }

    @SneakyThrows
    @Override
    public void register(RegisterDto registerDto, MultipartFile photo) {

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        String url = s3Service.uploadImage(photo);
        Attachment attachment = new Attachment();
        attachment.setUrl(url);
        Attachment savedAttachment = attachmentRepository.save(attachment);
        System.out.println("savedAttachment.getId() = " + savedAttachment.getId());

        User user = User.builder()
                .email(registerDto.getEmail())
                .password(registerDto.getPassword())
                .name(registerDto.getName())
                .role(Role.CHEF)
                .attachment(savedAttachment)
                .build();
        userRepository.save(user);
    }

}
