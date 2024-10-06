package com.corhuila.BackendSQL.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatriculaDto {
    private Integer id;
    private Integer materiaId;
    private Integer estudianteId;
    private Float notaDefinitiva;
}