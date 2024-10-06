package com.corhuila.BackendSQL.IService;

import com.corhuila.BackendSQL.Dto.NotaDto;
import com.corhuila.BackendSQL.Entity.Nota;

import java.util.List;

public interface INotaService {
    List<NotaDto> getAll();
    NotaDto findById(Integer id);
    Nota save(Nota nota);
    Nota update(Integer id, Nota nota);
    boolean delete(Integer id);
}