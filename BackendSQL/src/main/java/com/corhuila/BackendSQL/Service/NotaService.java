package com.corhuila.BackendSQL.Service;

import com.corhuila.BackendSQL.Dto.NotaDto;
import com.corhuila.BackendSQL.Entity.Nota;
import com.corhuila.BackendSQL.IRepository.INotaRepositorio;
import com.corhuila.BackendSQL.IService.INotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaService implements INotaService {
    @Autowired
    private INotaRepositorio iNotaRepositorio;

    @Override
    @Transactional
    public List<NotaDto> getAll() {
        return iNotaRepositorio.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public NotaDto findById(Integer id) {
        Nota nota = iNotaRepositorio.findById(id).orElse(null);
        return nota != null ? convertToDto(nota) : null;
    }

    @Override
    public Nota save(Nota nota) {
        return iNotaRepositorio.save(nota);
    }

    @Override
    public Nota update(Integer id, Nota nota) {
        Nota existingNota = iNotaRepositorio.findById(id).orElse(null);
        if (existingNota != null) {
            existingNota.setEstudiante(nota.getEstudiante());
            existingNota.setMateria(nota.getMateria());
            existingNota.setCorte(nota.getCorte());
            existingNota.setCalificacion(nota.getCalificacion());
            return iNotaRepositorio.save(existingNota);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (iNotaRepositorio.existsById(id)) {
            iNotaRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public NotaDto convertToDto(Nota nota) {
        NotaDto dto = new NotaDto();
        dto.setId(nota.getId());
        dto.setEstudianteId(nota.getEstudiante().getId());
        dto.setMateriaId(nota.getMateria().getId());
        dto.setCorte(nota.getCorte());
        dto.setCalificacion(nota.getCalificacion());
        return dto;
    }
}