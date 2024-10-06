package com.corhuila.BackendSQL.IRepository;

import com.corhuila.BackendSQL.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstudianteRepositorio extends JpaRepository<Estudiante, Integer> {}