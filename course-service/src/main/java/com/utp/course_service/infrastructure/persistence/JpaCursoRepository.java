package com.utp.course_service.infrastructure.persistence;

import com.utp.course_service.domain.model.Curso;
import com.utp.course_service.domain.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCursoRepository implements CursoRepository {

    private final SpringDataCursoRepository springDataRepo;

    @Override
    public Curso save(Curso curso) {
        return springDataRepo.save(curso);
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return springDataRepo.findById(id);
    }

    @Override
    public List<Curso> findByDocenteEmail(String email) {
        return springDataRepo.findByDocenteEmail(email);
    }
}


