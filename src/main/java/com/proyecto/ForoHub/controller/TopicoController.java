package com.proyecto.ForoHub.controller;

import com.proyecto.ForoHub.autor.Usuario;
import com.proyecto.ForoHub.autor.UsuarioRepository;
import com.proyecto.ForoHub.autor.DatosListadoUsuario;
import com.proyecto.ForoHub.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repositorioTopico;
    @Autowired
    private UsuarioRepository repoasitorioUsuario;

    @PostMapping
    public ResponseEntity<String> creandoTopico(@RequestBody @Valid DatosCreaciontopico datosCreaciontopico) {
        Optional<Topico> topicoExistente = repositorioTopico.findByTituloAndMensaje(
                datosCreaciontopico.titulo(), datosCreaciontopico.mensaje());

        if (topicoExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tópico duplicado: ya existe un tópico con el mismo título y mensaje");
        }

        Usuario usuario = repoasitorioUsuario.findById(datosCreaciontopico.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Topico topico = new Topico(datosCreaciontopico, usuario);

        repositorioTopico.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<DatosListarTopico> listandoTopicos() {
        List<Topico> topicos = repositorioTopico.findAll();
        return topicos.stream()
                .map(topico -> new DatosListarTopico(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaDeCreacion(),
                        topico.getEstatus(),
                        new DatosListadoUsuario(
                                topico.getUsuario().getId(),
                                topico.getUsuario().getNombre(),
                                topico.getUsuario().getEmail()
                        ),
                        topico.getCurso()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarTopicoPorId(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Id no es válido");
        }
        Optional<Topico> topicoOptional = repositorioTopico.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            var datosDeTopico = new DatosListarTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaDeCreacion(),
                    topico.getEstatus(),
                    new DatosListadoUsuario(
                            topico.getUsuario().getId(),
                            topico.getUsuario().getNombre(),
                            topico.getUsuario().getEmail()
                    ),
                    topico.getCurso());
            return ResponseEntity.ok(datosDeTopico);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        if (id == null || id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID no es válido");
        }
        Optional<Topico> topicoOptional = repositorioTopico.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.actualizarTopico(datosActualizarTopico);
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            var datosDeTopico = new DatosListarTopico(topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaDeCreacion(),
                    topico.getEstatus(),
                    new DatosListadoUsuario(
                            topico.getUsuario().getId(),
                            topico.getUsuario().getNombre(),
                            topico.getUsuario().getEmail()
                    ),
                    topico.getCurso());
            return ResponseEntity.ok(datosDeTopico);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico no encontrado");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topico = repositorioTopico.findById(id);
        if (topico.isPresent()) {
            repositorioTopico.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tópico no encontrado");
    }

}

