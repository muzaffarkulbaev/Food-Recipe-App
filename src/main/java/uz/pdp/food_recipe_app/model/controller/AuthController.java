package uz.pdp.food_recipe_app.model.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.model.enums.Role;
import uz.pdp.food_recipe_app.repo.UserRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @GetMapping("/google")
    public String googleLogin(@RequestParam String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("code", code);
        form.add("client_id", "437940866148-7sl9clhigifpv35d370n9mc08gr1cbq4.apps.googleusercontent.com");
        form.add("client_secret", "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");
        form.add("redirect_uri", "http://localhost:8080/api/auth/google");
        form.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token", request, Map.class);
        String accessToken = response.getBody().get("access_token").toString();

        // Step 2: Get user info
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setBearerAuth(accessToken);
        HttpEntity<Void> request2 = new HttpEntity<>(headers1);

        ResponseEntity<Map> response2 = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                request2,
                Map.class
        );

        Map<String, Object> userInfo = response2.getBody();

        if (userInfo != null && userInfo.containsKey("email")) {
            String email = userInfo.get("email").toString();
            String name = userInfo.get("name").toString();

            //Database ga saqlash
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isEmpty()) {
                User newUser = User.builder()
                        .email(email)
                        .name(name)
                        .password("GOOGLE_LOGIN")
                        .role(Role.CHEF)
                        .build();

                userRepository.save(newUser);
                System.out.println("✅ New user created: " + email);
            } else {
                System.out.println("ℹ️ User already exists: " + email);
            }
        }

        return "redirect:/home";
    }
}

