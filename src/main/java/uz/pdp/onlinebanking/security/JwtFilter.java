package uz.pdp.onlinebanking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.onlinebanking.entity.Users;
import uz.pdp.onlinebanking.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider tokenProvider;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String refreshToken = request.getHeader("RefreshToken");
        if (authorization !=null && refreshToken!=null){
            authorization = authorization.substring(7);
            refreshToken = refreshToken.substring(7);
            System.out.println("===");
            System.out.println(authorization);
            System.out.println("===");
            System.out.println(refreshToken);
            System.out.println("===");
            boolean validated = tokenProvider.validateJwtToken(authorization, refreshToken);
            if (validated) {
                Integer userId = tokenProvider.getUserIdFromToken(authorization, refreshToken);
                Users user = userRepository.findById(userId).orElse(null);
                if (user != null && user.isActive()) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
