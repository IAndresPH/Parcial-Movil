package com.corhuila.BackendSQL.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.corhuila.BackendSQL.Dto.EstudianteDto;
import com.corhuila.BackendSQL.Entity.Estudiante;
import com.corhuila.BackendSQL.IService.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.corhuila.BackendSQL.IRepository.IEstudianteRepositorio;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    private IEstudianteRepositorio iEstudianteRepositorio;

    @Transactional//Hibernate gestiona automáticamente las sesiones y conexiones necesarias para la interacción con la base de datos.
    @Override
    public List<EstudianteDto> getAll() {
        List<Estudiante> estudiantes = iEstudianteRepositorio.findAll();
        return  estudiantes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EstudianteDto findById(Integer id) {
        Estudiante estudiante = iEstudianteRepositorio.findById(id).orElse(null);
        return estudiante != null ? convertToDto(estudiante) : null;
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return iEstudianteRepositorio.save(estudiante);
    }

    @Override
    public Estudiante update(Integer idPerson, Estudiante estudiante) {
        Estudiante existingEstudiante = iEstudianteRepositorio.findById(idPerson).orElse(null);
        if (existingEstudiante != null) {
            existingEstudiante.setFirstName(estudiante.getFirstName());
            existingEstudiante.setLastName(estudiante.getLastName());
            existingEstudiante.setAge(estudiante.getAge());
            existingEstudiante.setGender(estudiante.getGender());
            return iEstudianteRepositorio.save(existingEstudiante);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Estudiante existingEstudiante = iEstudianteRepositorio.findById(id).orElse(null);
        if (existingEstudiante != null) {
            iEstudianteRepositorio.delete(existingEstudiante);
            return true; // Eliminación exitosa
        }
        return false; // No se encontró la persona con el ID dado
    }

    public Estudiante convertToEntity(EstudianteDto estudianteDto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(estudianteDto.getId());
        estudiante.setFirstName(estudianteDto.getFirstName());
        estudiante.setLastName(estudianteDto.getLastName());
        estudiante.setAge(estudianteDto.getAge());
        estudiante.setGender(estudianteDto.getGender());
        return estudiante;
    }

    public EstudianteDto convertToDto(Estudiante estudiante) {
        EstudianteDto estudianteDto = new EstudianteDto();
        estudianteDto.setId(estudiante.getId());
        estudianteDto.setFirstName(estudiante.getFirstName());
        estudianteDto.setLastName(estudiante.getLastName());
        estudianteDto.setAge(estudiante.getAge());
        estudianteDto.setGender(estudiante.getGender());
        return estudianteDto;
    }
}
