package com.egg.Estancias.repositorios;

import com.egg.Estancias.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository <Cliente, Integer> {
    
}
