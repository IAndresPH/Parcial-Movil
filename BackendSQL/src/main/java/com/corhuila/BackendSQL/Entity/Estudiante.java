package com.corhuila.BackendSQL.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Estudiante extends Base{
    @Column(nullable = false, length = 25, unique = false)
    private String firstName;

    @Column(nullable = false, length = 25, unique = false)
    private String lastName;

    @Column(nullable = false, length = 3, unique = false)
    private int age;

    @Column(nullable = false, length = 14, unique = false)
    private String gender;
}
