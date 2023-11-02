package com.egg.Estancias.repositorios;

import com.egg.Estancias.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepositorio extends JpaRepository <Comentario, Integer> {
    
}
