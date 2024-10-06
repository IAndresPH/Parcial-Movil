package com.corhuila.BackendSQL.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materia")
public class Materia extends Base {
    @Column(nullable = false, length = 25)
    private String nombre;

    @Column(nullable = false, length = 25)
    private String codigo;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private List<Nota> notas;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private List<Matricula> matriculas;

    public Materia(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
}