package com.corhuila.BackendSQL.Service;

import com.corhuila.BackendSQL.Dto.MateriaDto;
import com.corhuila.BackendSQL.Entity.Materia;
import com.corhuila.BackendSQL.IRepository.IMateriaRepositorio;
import com.corhuila.BackendSQL.IService.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaService implements IMateriaService {
    @Autowired
    private IMateriaRepositorio iMateriaRepositorio;

    @Override
    @Transactional
    public List<MateriaDto> getAll() {
        List<Materia> materias = iMateriaRepositorio.findAll();
        return materias.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MateriaDto findById(Integer id) {
        Materia materia = iMateriaRepositorio.findById(id).orElse(null);
        return materia != null ? convertToDto(materia) : null;
    }

    @Override
    public Materia save(Materia materia) {
        return iMateriaRepositorio.save(materia);
    }

    @Override
    public Materia update(Integer id, Materia materia) {
        Materia existingMateria = iMateriaRepositorio.findById(id).orElse(null);
        if (existingMateria != null) {
            existingMateria.setNombre(materia.getNombre());
            existingMateria.setCodigo(materia.getCodigo());
            return iMateriaRepositorio.save(existingMateria);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (iMateriaRepositorio.existsById(id)) {
            iMateriaRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public MateriaDto convertToDto(Materia materia) {
        MateriaDto dto = new MateriaDto();
        dto.setId(materia.getId());
        dto.setNombre(materia.getNombre());
        dto.setCodigo(materia.getCodigo());
        return dto;
    }
}