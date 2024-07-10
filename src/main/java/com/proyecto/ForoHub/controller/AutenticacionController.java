package com.proyecto.ForoHub.controller;

import com.proyecto.ForoHub.autor.DatosAutenticacionUsuario;
import com.proyecto.ForoHub.autor.DatosRegistroUsuario;
import com.proyecto.ForoHub.autor.Usuario;
import com.proyecto.ForoHub.security.DatosJWTToken;
import com.proyecto.ForoHub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity authenticateUser(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            datosAutenticacionUsuario.nombre(),
                            datosAutenticacionUsuario.contraseña()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            var token = tokenService.generarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new DatosJWTToken(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
        }
    }
}



