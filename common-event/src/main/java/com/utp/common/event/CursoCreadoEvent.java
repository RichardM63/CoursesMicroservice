package com.utp.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoCreadoEvent {
    private Long cursoId;
    private String nombre;
    private String docenteEmail;
}
