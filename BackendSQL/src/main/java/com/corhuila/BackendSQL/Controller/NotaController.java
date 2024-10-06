package com.corhuila.BackendSQL.Controller;

import com.corhuila.BackendSQL.Dto.NotaDto;
import com.corhuila.BackendSQL.Entity.Nota;
import com.corhuila.BackendSQL.Service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nota")
@CrossOrigin(origins = "*")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public ResponseEntity<List<NotaDto>> getAll() {
        return ResponseEntity.ok(notaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDto> findById(@PathVariable int id) {
        NotaDto notaDto = notaService.findById(id);
        return notaDto != null ? ResponseEntity.ok(notaDto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<NotaDto> save(@RequestBody NotaDto notaDto) {
        Nota nota = new Nota(); // Convertir desde DTO a entidad
        // Asigna los atributos necesarios
        Nota savedNota = notaService.save(nota);
        NotaDto savedNotaDto = notaService.convertToDto(savedNota);
        return ResponseEntity.ok(savedNotaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotaDto> update(@PathVariable Integer id, @RequestBody NotaDto notaDto) {
        if (!id.equals(notaDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Nota updatedNota = notaService.update(id, new Nota());
        return updatedNota != null ? ResponseEntity.ok(notaService.convertToDto(updatedNota)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return notaService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
