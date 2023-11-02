package com.egg.Estancias.repositorios;

import com.egg.Estancias.entidades.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepositorio extends JpaRepository <Familia, Integer> {
    
}
