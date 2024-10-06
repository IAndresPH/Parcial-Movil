package com.corhuila.BackendSQL.Controller;

import com.corhuila.BackendSQL.Dto.MatriculaDto;
import com.corhuila.BackendSQL.Entity.Matricula;
import com.corhuila.BackendSQL.Service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matricula")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaDto>> getAll() {
        List<MatriculaDto> matriculaDtos = matriculaService.getAll();
        return ResponseEntity.ok(matriculaDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDto> findById(@PathVariable Integer id) {
        MatriculaDto matriculaDto = matriculaService.findById(id);
        return matriculaDto != null ? ResponseEntity.ok(matriculaDto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<MatriculaDto> save(@RequestBody MatriculaDto matriculaDto) {
        Matricula matricula = matriculaService.convertToEntity(matriculaDto);
        Matricula savedMatricula = matriculaService.save(matricula);
        return ResponseEntity.ok(matriculaService.convertToDto(savedMatricula));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatriculaDto> update(@PathVariable Integer id, @RequestBody MatriculaDto matriculaDto) {
        if (!id.equals(matriculaDto.getId())) {
            return ResponseEntity.badRequest().build(); // Si los IDs no coinciden, retornar un bad request
        }
        Matricula updatedMatricula = matriculaService.update(id, matriculaService.convertToEntity(matriculaDto));
        return updatedMatricula != null ? ResponseEntity.ok(matriculaService.convertToDto(updatedMatricula)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = matriculaService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}