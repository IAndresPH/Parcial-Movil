package com.corhuila.BackendSQL.IRepository;

import com.corhuila.BackendSQL.Entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMateriaRepositorio extends JpaRepository<Materia, Integer> {}
