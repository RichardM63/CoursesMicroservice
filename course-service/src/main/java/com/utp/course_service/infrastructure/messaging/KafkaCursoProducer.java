package com.utp.course_service.infrastructure.messaging;

import com.utp.common.event.CursoCreadoEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaCursoProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCursoProducer.class);
    private static final String TOPIC = "curso.creado";

    private final KafkaTemplate<String, CursoCreadoEvent> kafkaTemplate;

    public void enviarCursoCreado(CursoCreadoEvent evento) {
        LOGGER.info("✅ Enviando evento de curso creado a Kafka: {}", evento);
        kafkaTemplate.send(TOPIC, evento);
    }
}
