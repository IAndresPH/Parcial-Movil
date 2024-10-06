package com.corhuila.BackendSQL.Controller;

import com.corhuila.BackendSQL.Dto.MateriaDto;
import com.corhuila.BackendSQL.Entity.Materia;
import com.corhuila.BackendSQL.Service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materia")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> getAll() {
        return ResponseEntity.ok(materiaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> findById(@PathVariable int id) {
        MateriaDto materiaDto = materiaService.findById(id);
        return materiaDto != null ? ResponseEntity.ok(materiaDto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<MateriaDto> save(@RequestBody MateriaDto materiaDto) {
        Materia materia = new Materia();
        materia.setNombre(materiaDto.getNombre());
        materia.setCodigo(materiaDto.getCodigo());
        Materia savedMateria = materiaService.save(materia);
        MateriaDto savedMateriaDto = materiaService.convertToDto(savedMateria);
        return ResponseEntity.ok(savedMateriaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDto> update(@PathVariable Integer id, @RequestBody MateriaDto materiaDto) {
        if (!id.equals(materiaDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Materia updatedMateria = materiaService.update(id, new Materia(materiaDto.getNombre(), materiaDto.getCodigo()));
        return updatedMateria != null ? ResponseEntity.ok(materiaService.convertToDto(updatedMateria)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return materiaService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}