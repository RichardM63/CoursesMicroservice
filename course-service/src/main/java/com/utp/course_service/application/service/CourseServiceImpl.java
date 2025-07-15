package com.utp.course_service.application.service;

import com.utp.common.event.CursoCreadoEvent;
import com.utp.course_service.domain.model.Curso;
import com.utp.course_service.domain.repository.CursoRepository;
import com.utp.course_service.dto.CrearCursoDTO;
import com.utp.course_service.dto.CursoResponseDTO;
import com.utp.course_service.infrastructure.messaging.KafkaCursoProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements ICourseService {

    private final CursoRepository cursoRepository;
    private final KafkaCursoProducer kafkaProducer;

    @Override
    public CursoResponseDTO crearCurso(CrearCursoDTO dto) {
        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());
        curso.setDescripcion(dto.getDescripcion());
        curso.setDocenteEmail(dto.getDocenteEmail());

        Curso cursoGuardado = cursoRepository.save(curso);

        // Emitimos el evento a Kafka
        CursoCreadoEvent event = new CursoCreadoEvent(
                cursoGuardado.getId(),
                cursoGuardado.getNombre(),
                cursoGuardado.getDocenteEmail()
        );
        kafkaProducer.enviarCursoCreado(event);

        return new CursoResponseDTO(
                cursoGuardado.getId(),
                cursoGuardado.getNombre(),
                cursoGuardado.getDescripcion(),
                cursoGuardado.getDocenteEmail()
        );
    }

    @Override
    public List<CursoResponseDTO> listarCursosPorDocente(String email) {
        return cursoRepository.findByDocenteEmail(email).stream()
                .map(curso -> new CursoResponseDTO(
                        curso.getId(),
                        curso.getNombre(),
                        curso.getDescripcion(),
                        curso.getDocenteEmail()
                ))
                .toList();
    }
}
