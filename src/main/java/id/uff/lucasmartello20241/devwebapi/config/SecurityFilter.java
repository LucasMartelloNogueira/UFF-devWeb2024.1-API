package id.uff.lucasmartello20241.devwebapi.config;

import id.uff.lucasmartello20241.devwebapi.exceptions.NotFoundException;
import id.uff.lucasmartello20241.devwebapi.model.entities.User;
import id.uff.lucasmartello20241.devwebapi.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = recoverToken(request);
            if (token != null) {

                String username = tokenService.validarToken(token);
                Optional<User> user = userRepository.findByUsername(username);
                if (!user.isPresent()) {
                    throw new NotFoundException(
                        "Usuário " + username + " não encontrado no banco de dados.");
                }
      
                var authentication = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("===> Salvou o token no contexto (request) do spring security.");
            }
            else {
                System.out.println("===> Sem token.");
            }
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            System.out.println(">>>> Classe de exceção: " + e.getClass().getName());
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");  
    }
}
