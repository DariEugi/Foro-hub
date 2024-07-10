package com.proyecto.ForoHub.topico;

import com.proyecto.ForoHub.autor.DatosListadoUsuario;

import java.time.LocalDate;

public record DatosListarTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDate fechaDeCreacion,
    Status estatus,
    DatosListadoUsuario usuario,
    String curso
)
{}
