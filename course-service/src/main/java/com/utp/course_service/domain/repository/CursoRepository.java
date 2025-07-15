package com.utp.course_service.domain.repository;

import com.utp.course_service.domain.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoRepository {
    Curso save(Curso curso);
    Optional<Curso> findById(Long id);
    List<Curso> findByDocenteEmail(String email);
}

