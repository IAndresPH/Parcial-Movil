package com.corhuila.BackendSQL.Service;

import com.corhuila.BackendSQL.Dto.MatriculaDto;
import com.corhuila.BackendSQL.Entity.Estudiante;
import com.corhuila.BackendSQL.Entity.Materia;
import com.corhuila.BackendSQL.Entity.Matricula;
import com.corhuila.BackendSQL.IRepository.IEstudianteRepositorio;
import com.corhuila.BackendSQL.IRepository.IMateriaRepositorio;
import com.corhuila.BackendSQL.IRepository.IMatriculaRepositorio;
import com.corhuila.BackendSQL.IService.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatriculaService implements IMatriculaService {
    @Autowired
    private IMatriculaRepositorio iMatriculaRepositorio;

    @Autowired
    private IEstudianteRepositorio iEstudianteRepositorio;

    @Autowired
    private IMateriaRepositorio iMateriaRepositorio;

    @Override
    @Transactional
    public List<MatriculaDto> getAll() {
        return iMatriculaRepositorio.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MatriculaDto findById(Integer id) {
        Matricula matricula = iMatriculaRepositorio.findById(id).orElse(null);
        return matricula != null ? convertToDto(matricula) : null;
    }

    @Override
    public Matricula save(Matricula matricula) {
        return iMatriculaRepositorio.save(matricula);
    }

    @Override
    public Matricula update(Integer id, Matricula matricula) {
        Matricula existingMatricula = iMatriculaRepositorio.findById(id).orElse(null);
        if (existingMatricula != null) {
            existingMatricula.setMateria(matricula.getMateria());
            existingMatricula.setEstudiante(matricula.getEstudiante());
            existingMatricula.setNotaDefinitiva(matricula.getNotaDefinitiva());
            return iMatriculaRepositorio.save(existingMatricula);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (iMatriculaRepositorio.existsById(id)) {
            iMatriculaRepositorio.deleteById(id);
            return true;
        }
        return false;
    }

    public Matricula convertToEntity(MatriculaDto matriculaDto) {
        Matricula matricula = new Matricula();
        matricula.setId(matriculaDto.getId());

        Optional<Estudiante> estudiante = iEstudianteRepositorio.findById(matriculaDto.getEstudianteId());
        estudiante.ifPresent(matricula::setEstudiante);

        Optional<Materia> materia = iMateriaRepositorio.findById(matriculaDto.getMateriaId());
        materia.ifPresent(matricula::setMateria);

        matricula.setNotaDefinitiva(matriculaDto.getNotaDefinitiva());

        return matricula;
    }

    public MatriculaDto convertToDto(Matricula matricula) {
        MatriculaDto dto = new MatriculaDto();
        dto.setId(matricula.getId());
        dto.setMateriaId(matricula.getMateria().getId());
        dto.setEstudianteId(matricula.getEstudiante().getId());
        dto.setNotaDefinitiva(matricula.getNotaDefinitiva());
        return dto;
    }
}