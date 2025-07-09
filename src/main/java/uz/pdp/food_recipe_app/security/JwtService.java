package uz.pdp.food_recipe_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.food_recipe_app.model.entity.Role;
import uz.pdp.food_recipe_app.model.entity.User;
import uz.pdp.food_recipe_app.repo.UserRepository;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;

    public String generateToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return "Bearer " + Jwts.builder()
                .subject(email)
                .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey())
                .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor("123123123123123123123123123123123".getBytes());
    }

    public String refreshToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return "Bearer " + Jwts.builder()
                .subject(email)
                .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 24*7))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public User getUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String email = claims.getSubject();
        String roles = (String) claims.get("roles");
        List<Role> authorities = Arrays.stream(roles.split(",")).map(Role::new).toList();
        return new User(email, authorities);
    }
}
