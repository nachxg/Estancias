package com.egg.Estancias.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.Estancias.entidades.Casa;
import com.egg.Estancias.entidades.Familia;
import com.egg.Estancias.entidades.Usuario;
import com.egg.Estancias.excepciones.MiExcepcion;
import com.egg.Estancias.repositorios.CasaRepositorio;
import com.egg.Estancias.repositorios.FamiliaRepositorio;
import com.egg.Estancias.repositorios.UsuarioRepositorio;

@Service
public class FamiliaServicio {

    @Autowired
    private FamiliaRepositorio familiaRepositorio;

    @Autowired
    private CasaRepositorio casaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearFamilia(String nombre, int edadMin, int edadMax, int numHijos, String email, Integer idCasa,
            Integer idUsuario) throws MiExcepcion {

        validar(nombre, edadMin, edadMax, numHijos, email, idCasa, idUsuario);

        Familia familia = new Familia();
        Casa casa = new Casa();
        Usuario usuario = new Usuario();

        Optional<Casa> respuestaCasa = casaRepositorio.findById(idCasa);
        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idUsuario);

        if (respuestaCasa.isPresent()) {
            casa = respuestaCasa.get();
        }

        if (respuestaUsuario.isPresent()) {
            usuario = respuestaUsuario.get();
        }

        familia.setNombre(nombre);
        familia.setEdadMin(edadMin);
        familia.setEdadMax(edadMax);
        familia.setNumHijos(numHijos);
        familia.setEmail(email);
        familia.setUsuario(usuario);
        familia.setCasa(casa);
        familiaRepositorio.save(familia);

    }

    public List<Familia> consultarFamilias() {
        List<Familia> familias = new ArrayList<>();
        familias = familiaRepositorio.findAll();
        return familias;
    }

    @Transactional
    public void eliminarFamilia(Integer idFamilia) {

        Optional<Familia> respuesta = familiaRepositorio.findById(idFamilia);

        if (respuesta.isPresent()) {
            familiaRepositorio.deleteById(idFamilia);
        }

    }

    @Transactional
    public void modificarFamilia(Integer idFamilia, String nombre, int edadMin, int edadMax, int numHijos, String email,
            Integer idCasa, Integer idUsuario) throws MiExcepcion {

        validar(nombre, edadMin, edadMax, numHijos, email, idCasa, idUsuario);

        Optional<Casa> respuestaCasa = casaRepositorio.findById(idCasa);
        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idUsuario);
        Optional<Familia> respuestaFamilia = familiaRepositorio.findById(idFamilia);
        Casa casa = new Casa();
        Usuario usuario = new Usuario();

        if (respuestaCasa.isPresent()) {
            casa = respuestaCasa.get();
        }

        if (respuestaUsuario.isPresent()) {
            usuario = respuestaUsuario.get();
        }

        if (respuestaFamilia.isPresent()) {

            Familia familia = respuestaFamilia.get();

            familia.setNombre(nombre);
            familia.setEdadMin(edadMin);
            familia.setEdadMax(edadMax);
            familia.setNumHijos(numHijos);
            familia.setEmail(email);
            familia.setUsuario(usuario);
            familia.setCasa(casa);

            familiaRepositorio.save(familia);

        }

    }

    public void validar(String nombre, Integer edadMin, Integer edadMax, Integer numHijos, String email, Integer idCasa,
            Integer idUsuario) throws MiExcepcion {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiExcepcion("El campo 'Nombre' no puede ser nulo o estar vacío.");
        }
        
        if (edadMin == null) {
            throw new MiExcepcion("El campo 'Edad mínima' no puede ser nulo o estar vacío.");
        } 

        if (edadMax == null) {
            throw new MiExcepcion("El campo 'Edad máxima' no puede ser nulo o estar vacío.");
        }

        if (numHijos == null) {
            throw new MiExcepcion("El campo 'Número de hijos' no puede ser nulo o estar vacío.");
        }

        if (email.isEmpty() || email == null) {
            throw new MiExcepcion("El campo 'Email' no puede ser nulo o estar vacío.");
        }

        if (idCasa == null) {
            throw new MiExcepcion("El campo 'Casa' no puede ser nulo o estar vacío.");
        }

        if (idUsuario == null) {
            throw new MiExcepcion("El campo 'Usuario' no puede ser nulo o estar vacío.");
        }

    }   

}
