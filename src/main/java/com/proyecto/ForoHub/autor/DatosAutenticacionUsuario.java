package com.proyecto.ForoHub.autor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAutenticacionUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String contraseña
) {
}
