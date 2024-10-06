package com.corhuila.BackendSQL.IService;

import com.corhuila.BackendSQL.Dto.MateriaDto;
import com.corhuila.BackendSQL.Entity.Materia;

import java.util.List;

public interface IMateriaService {
    List<MateriaDto> getAll();
    MateriaDto findById(Integer id);
    Materia save(Materia materia);
    Materia update(Integer id, Materia materia);
    boolean delete(Integer id);
}