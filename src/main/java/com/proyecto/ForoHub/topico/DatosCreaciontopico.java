package com.proyecto.ForoHub.topico;

import com.proyecto.ForoHub.autor.DatosRegistroUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DatosCreaciontopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        LocalDate fechaDeCreacion,
        @NotNull
        Status estatus,
        @NotNull
        @Valid
        Long idUsuario,
        @NotBlank
        String curso
) {
}
