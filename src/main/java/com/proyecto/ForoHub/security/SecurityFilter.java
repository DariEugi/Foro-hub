package com.proyecto.ForoHub.security;

import com.proyecto.ForoHub.autor.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

     @Autowired
    private TokenService tokenService;
     @Autowired
     private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeder = request.getHeader("Authorization");

        if (authHeder != null){
            var token = authHeder.replace("Bearer ","");
            var nombreDeUsuario = tokenService.getSubject(token);
            if (nombreDeUsuario != null){
                // el token esta valido
                var usuario = usuarioRepository.findByNombre(nombreDeUsuario);
                var authentication= new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
