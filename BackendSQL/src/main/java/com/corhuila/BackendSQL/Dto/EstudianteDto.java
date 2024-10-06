package com.corhuila.BackendSQL.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
}