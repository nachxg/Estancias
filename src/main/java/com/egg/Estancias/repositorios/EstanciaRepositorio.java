package com.egg.Estancias.repositorios;

import com.egg.Estancias.entidades.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanciaRepositorio extends JpaRepository <Estancia, Integer> {
    
}
