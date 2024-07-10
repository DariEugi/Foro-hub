package com.proyecto.ForoHub.topico;

import com.proyecto.ForoHub.autor.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechaDeCreacion;
    @Enumerated(EnumType.STRING)
    private Status estatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String curso;

    public Topico(DatosCreaciontopico datosCreaciontopico, Usuario usuario) {
        this.titulo= datosCreaciontopico.titulo();
        this.mensaje = datosCreaciontopico.mensaje();
        this.fechaDeCreacion=datosCreaciontopico.fechaDeCreacion();
        this.estatus= datosCreaciontopico.estatus();
        this.usuario= usuario;
        this.curso = datosCreaciontopico.curso();

    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.estatus() != null) {
            this.estatus = datosActualizarTopico.estatus();
        }
    }

}
