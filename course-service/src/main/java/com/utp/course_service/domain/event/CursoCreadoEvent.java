package com.utp.course_service.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CursoCreadoEvent {
    private Long cursoId;
    private String nombre;
    private String docenteEmail;
}
