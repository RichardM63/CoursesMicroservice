package com.utp.log_service.infrastructure.messaging;

import com.utp.common.event.CursoCreadoEvent;
import com.utp.log_service.domain.model.CursoLog;
import com.utp.log_service.domain.repository.CursoLogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CursoLogConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CursoLogConsumer.class);
    private final CursoLogRepository cursoLogRepository;

    @KafkaListener(
            topics = "curso.creado",
            groupId = "log-service-group",
            containerFactory = "cursoKafkaListenerContainerFactory"
    )
    public void consumirCursoCreado(CursoCreadoEvent evento) {
        LOGGER.info("📥 Evento recibido: {}", evento);

        CursoLog log = CursoLog.builder()
                .cursoId(evento.getCursoId())
                .nombreCurso(evento.getNombre())
                .docenteEmail(evento.getDocenteEmail())
                .fechaCreacion(LocalDateTime.now())
                .build();

        cursoLogRepository.save(log);

        LOGGER.info("✅ Log del curso guardado correctamente");
    }
}


