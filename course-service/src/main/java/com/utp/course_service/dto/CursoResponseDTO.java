package com.utp.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CursoResponseDTO {

    @Schema(description = "ID del curso", example = "1")
    private Long id;

    @Schema(description = "Nombre del curso", example = "Programación Web")
    private String nombre;

    @Schema(description = "Descripción del curso", example = "Curso para aprender Spring Boot")
    private String descripcion;

    @Schema(description = "Correo del docente", example = "juan.perez@utp.edu.pe")
    private String docenteEmail;
}