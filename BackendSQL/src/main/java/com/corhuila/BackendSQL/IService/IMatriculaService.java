package com.corhuila.BackendSQL.IService;

import com.corhuila.BackendSQL.Dto.MatriculaDto;
import com.corhuila.BackendSQL.Entity.Matricula;

import java.util.List;

public interface IMatriculaService {
    List<MatriculaDto> getAll();
    MatriculaDto findById(Integer id);
    Matricula save(Matricula matricula);
    Matricula update(Integer id, Matricula matricula);
    boolean delete(Integer id);
}