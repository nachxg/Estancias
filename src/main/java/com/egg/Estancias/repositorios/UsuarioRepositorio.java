
package com.egg.Estancias.repositorios;

import com.egg.Estancias.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository <Usuario, Integer> {
    @Query("SELECT u from Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);

    @Query("SELECT u from Usuario u WHERE u.username = :username")
    public Usuario buscarPorUsuario(@Param("username") String username);
    
}
