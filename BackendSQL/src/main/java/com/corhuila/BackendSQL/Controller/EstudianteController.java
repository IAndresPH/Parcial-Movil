package com.corhuila.BackendSQL.Controller;

import java.util.List;

import com.corhuila.BackendSQL.Dto.EstudianteDto;
import com.corhuila.BackendSQL.Entity.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.corhuila.BackendSQL.Service.EstudianteService;

@RestController
@RequestMapping("/api/estudiante")
@CrossOrigin(origins = "*")
public class EstudianteController {
    @Autowired
    private EstudianteService personService;

    @GetMapping
    public ResponseEntity<List<EstudianteDto>> getAll() {
        List<EstudianteDto> estudianteDtos = personService.getAll();
        return ResponseEntity.ok(estudianteDtos);
    }

    @GetMapping("/{idPerson}")
    public ResponseEntity<EstudianteDto> findById(@PathVariable int idPerson) {
        EstudianteDto estudianteDto = personService.findById(idPerson);
        if (estudianteDto != null) {
            return ResponseEntity.ok(estudianteDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<EstudianteDto> save(@RequestBody EstudianteDto estudianteDto) {
        Estudiante estudiante = personService.convertToEntity(estudianteDto);
        Estudiante savedEstudiante = personService.save(estudiante);
        EstudianteDto savedEstudianteDto = personService.convertToDto(savedEstudiante);
        return ResponseEntity.ok(savedEstudianteDto);
    }

    @PutMapping("/{idPerson}")
    public ResponseEntity<EstudianteDto> update(@PathVariable Integer idPerson, @RequestBody EstudianteDto estudianteDto) {
        if (!idPerson.equals(estudianteDto.getId())) {
            return ResponseEntity.badRequest().build(); // Retornar un bad request si los IDs no coinciden
        }
        Estudiante updatedEstudiante = personService.update(idPerson, personService.convertToEntity(estudianteDto));
        if (updatedEstudiante != null) {
            return ResponseEntity.ok(personService.convertToDto(updatedEstudiante));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = personService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Eliminación exitosa
        } else {
            return ResponseEntity.notFound().build(); // No se encontró la persona con el ID dado
        }
    }
}