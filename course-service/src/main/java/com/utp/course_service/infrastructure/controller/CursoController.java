package com.utp.course_service.infrastructure.controller;

import com.utp.course_service.application.service.ICourseService;
import com.utp.course_service.dto.CrearCursoDTO;
import com.utp.course_service.dto.CursoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICourseService courseService;

    @Operation(
            summary = "Crea un nuevo curso",
            description = "Registra un nuevo curso asociado a un docente.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Curso creado exitosamente",
                            content = @Content(schema = @Schema(implementation = CursoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public ResponseEntity<CursoResponseDTO> crearCurso(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del curso a crear",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CrearCursoDTO.class),
                            examples = @ExampleObject(value = """
                                {
                                  "nombre": "Programación Web",
                                  "descripcion": "Curso para aprender Spring Boot",
                                  "docenteEmail": "juan.perez@utp.edu.pe"
                                }
                            """)
                    )
            )
            @RequestBody CrearCursoDTO dto
    ) {
        CursoResponseDTO curso = courseService.crearCurso(dto);
        return ResponseEntity.ok(curso);
    }

    @Operation(
            summary = "Lista cursos por docente",
            description = "Obtiene todos los cursos creados por un docente según su correo electrónico.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de cursos obtenida",
                            content = @Content(schema = @Schema(implementation = CursoResponseDTO.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "No se encontraron cursos para el docente")
            }
    )
    @GetMapping("/docente/{email}")
    public ResponseEntity<List<CursoResponseDTO>> listarPorDocente(
            @Parameter(description = "Correo del docente", example = "juan.perez@utp.edu.pe")
            @PathVariable("email") String email
    ) {
        List<CursoResponseDTO> cursos = courseService.listarCursosPorDocente(email);
        return ResponseEntity.ok(cursos);
    }
}