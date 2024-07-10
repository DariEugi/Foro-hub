package com.proyecto.ForoHub.controller;

import com.proyecto.ForoHub.autor.DatosRegistroUsuario;
import com.proyecto.ForoHub.autor.Usuario;
import com.proyecto.ForoHub.autor.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Long>> registroUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {

        String contraseñaEncriptada = passwordEncoder.encode(datosRegistroUsuario.contraseña());
        Usuario usuario = new Usuario(datosRegistroUsuario.nombre(), datosRegistroUsuario.email(), contraseñaEncriptada);
        repositorioUsuario.save(usuario);

        Map<String, Long> response = new HashMap<>();
        response.put("id", usuario.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


