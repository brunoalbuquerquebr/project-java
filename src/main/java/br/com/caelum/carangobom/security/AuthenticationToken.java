package br.com.caelum.carangobom.security;

import br.com.caelum.carangobom.domain.User;
import br.com.caelum.carangobom.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationToken extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository repository;


    public AuthenticationToken(TokenService tokenService,  UserRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.repository = usuarioRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean valid = tokenService.isTokenValid(token);

        if(valid) {
            authenClient(token);
        }


        filterChain.doFilter(request, response);

    }

    private void authenClient(String token) {
        Long idUser = tokenService.getIdUser(token);
        Optional<User> usuario = repository.findById(idUser);
        if(usuario.isPresent()) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }



    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
