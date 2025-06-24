package uz.pdp.food_recipe_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth ->
            auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
    );
    return http.build();
    }
}
