package com.utp.course_service.application.service;

import com.utp.course_service.dto.CrearCursoDTO;
import com.utp.course_service.dto.CursoResponseDTO;

import java.util.List;

public interface ICourseService {
    CursoResponseDTO crearCurso(CrearCursoDTO dto);
    List<CursoResponseDTO> listarCursosPorDocente(String email);
}
