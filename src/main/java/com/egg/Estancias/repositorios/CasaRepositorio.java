
package com.egg.Estancias.repositorios;

import com.egg.Estancias.entidades.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepositorio extends JpaRepository <Casa, Integer> {
    
}
