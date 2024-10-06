package com.corhuila.BackendSQL.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotaDto {
    private Integer id;
    private Integer estudianteId;
    private Integer materiaId;
    private int corte;
    private float calificacion;
}