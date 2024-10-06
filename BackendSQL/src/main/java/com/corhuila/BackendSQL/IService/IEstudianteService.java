package com.corhuila.BackendSQL.IService;

import com.corhuila.BackendSQL.Dto.EstudianteDto;
import com.corhuila.BackendSQL.Entity.Estudiante;

import java.util.List;

public interface IEstudianteService {
    List<EstudianteDto> getAll();
    EstudianteDto findById(Integer id);
    Estudiante save(Estudiante estudiante);
    Estudiante update(Integer idPerson, Estudiante estudiante);
    boolean delete(Integer id);
}