package uz.pdp.food_recipe_app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.food_recipe_app.model.entity.User;

import java.io.IOException;

@Slf4j
@Component
public class MySecurityFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public MySecurityFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

//        String path = request.getRequestURI();
//
//        // Пропускаем Swagger и H2 без проверки токена
//        if (path.startsWith("/swagger-ui")
//                || path.startsWith("/v3/api-docs")
//                || path.startsWith("/swagger-resources")
//                || path.startsWith("/webjars")
//                || path.startsWith("/h2-console")
//                || path.startsWith("/api/v1/auth")
//        ) {
//            System.out.println("path = " + path);
//            filterChain.doFilter(request, response);
//            return;
//        }

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                if (jwtService.validateToken(token)) {
                    User user = jwtService.getUserFromToken(token);
                    var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                log.error("not valid token");
            }
        }

        filterChain.doFilter(request, response);
    }

}
