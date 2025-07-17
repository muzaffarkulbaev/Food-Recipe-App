package uz.pdp.food_recipe_app.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.food_recipe_app.model.dto.request.LoginDto;
import uz.pdp.food_recipe_app.model.dto.request.RegisterDto;
import uz.pdp.food_recipe_app.model.dto.response.LoginResponse;
import uz.pdp.food_recipe_app.model.entity.Attachment;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.model.enums.Role;
import uz.pdp.food_recipe_app.repo.AttachmentRepository;
import uz.pdp.food_recipe_app.repo.RoleRepository;
import uz.pdp.food_recipe_app.repo.UserRepository;
import uz.pdp.food_recipe_app.security.JwtService;
import uz.pdp.food_recipe_app.service.abstractions.AuthService;
import uz.pdp.food_recipe_app.service.abstractions.S3Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginDto loginDto) {
        var auth = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()
        );
        System.out.println("auth.getCredentials() = " + auth.getCredentials());
        Authentication authenticate = authenticationManager.authenticate(auth);
        System.out.println("authenticate.getName() = " + authenticate.getName());
        String token = jwtService.generateToken(loginDto.getEmail());
        System.out.println("token = " + token);
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        Long userId = user.getId();
        if (user.getRoles().get(0).getName().equals("ROLE_ADMIN")) return new LoginResponse(token,userId,true);
        return new LoginResponse(token,userId,false);
    }

    @SneakyThrows
    @Override
    public void register(RegisterDto registerDto) {

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

//        String url = s3Service.uploadImage(photo);
//        Attachment attachment = new Attachment();
//        attachment.setUrl(url);
//        Attachment savedAttachment = attachmentRepository.save(attachment);
//        System.out.println("savedAttachment.getId() = " + savedAttachment.getId());
        Attachment attachment = new Attachment();
        Attachment savedAttachment = attachmentRepository.save(attachment);

        User user = User.builder()
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .name(registerDto.getName())
                .attachment(savedAttachment)
                .build();
        user.getRoles().add(roleRepository.findByName("ROLE_CHEF"));
        userRepository.save(user);
    }

}
