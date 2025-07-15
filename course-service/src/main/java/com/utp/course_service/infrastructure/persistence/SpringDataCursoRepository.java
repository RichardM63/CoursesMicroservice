package com.utp.course_service.infrastructure.persistence;

import com.utp.course_service.domain.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataCursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByDocenteEmail(String docenteEmail);
}
