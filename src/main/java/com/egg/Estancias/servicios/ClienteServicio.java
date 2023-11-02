package com.egg.Estancias.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.Estancias.entidades.Cliente;
import com.egg.Estancias.entidades.Estancia;
import com.egg.Estancias.entidades.Usuario;
import com.egg.Estancias.excepciones.MiExcepcion;
import com.egg.Estancias.repositorios.ClienteRepositorio;
import com.egg.Estancias.repositorios.UsuarioRepositorio;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void crearCliente(String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, Integer idUsuario) throws MiExcepcion {

        validar(nombre, calle, numero, codigoPostal, ciudad, pais, idUsuario);

        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        List<Estancia> estancias = new ArrayList<>();

        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idUsuario);

        if (respuestaUsuario.isPresent()) {
            usuario = respuestaUsuario.get();
        }

        cliente.setNombre(nombre);
        cliente.setCalle(calle);
        cliente.setNumero(numero);
        cliente.setCodigoPostal(codigoPostal);
        cliente.setCiudad(ciudad);
        cliente.setPais(pais);
        cliente.setEstancia(estancias);
        cliente.setUsuario(usuario);

        clienteRepositorio.save(cliente);

    }

    public List<Cliente> consultarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Transactional
    public void eliminarCliente(Integer idCliente) {

        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(idCliente);

        if (respuestaCliente.isPresent()) {
            clienteRepositorio.deleteById(idCliente);
        }

    }

    @Transactional
    public void modificarCliente(Integer idCliente, String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, Integer idUsuario) throws MiExcepcion {

        validar(nombre, calle, numero, codigoPostal, ciudad, pais, idUsuario);

        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();

        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(idCliente);
        Optional<Usuario> respuestaUsuario = usuarioRepositorio.findById(idUsuario);

        if (respuestaUsuario.isPresent()) {
            usuario = respuestaUsuario.get();
        }

        if (respuestaCliente.isPresent()) {

            cliente = respuestaCliente.get();

            cliente.setNombre(nombre);
            cliente.setCalle(calle);
            cliente.setNumero(numero);
            cliente.setCodigoPostal(codigoPostal);
            cliente.setCiudad(ciudad);
            cliente.setPais(pais);
            cliente.setUsuario(usuario);

            clienteRepositorio.save(cliente);
        }
        
    }

    public void validar(String nombre, String calle, Integer numero, String codigoPostal, String ciudad, String pais, Integer idUsuario) throws MiExcepcion {
        
        if (nombre.isEmpty() || nombre == null) {
            throw new MiExcepcion("El campo 'Nombre' no puede ser nulo o estar vacío.");
        }

        if (calle.isEmpty() || calle == null) {
            throw new MiExcepcion("El campo 'Calle' no puede ser nulo o estar vacío.");
        }

        if (numero == null) {
            throw new MiExcepcion("El campo 'Numero' no puede ser nulo o estar vacío.");
        }

        if (codigoPostal.isEmpty() || codigoPostal == null) {
            throw new MiExcepcion("El campo 'Código postal' no puede ser nulo o estar vacío.");
        }

        if (ciudad.isEmpty() || codigoPostal == null) {
            throw new MiExcepcion("El campo 'Ciudad' no puede ser nulo o estar vacío.");
        }

        if (pais.isEmpty() || pais == null) {
            throw new MiExcepcion("El campo 'País' no puede ser nulo o estar vacío.");
        }

        if (ciudad.isEmpty() || codigoPostal == null) {
            throw new MiExcepcion("El campo 'Ciudad' no puede ser nulo o estar vacío.");
        }

        if (idUsuario == null) {
            throw new MiExcepcion("El campo 'Usuario' no puede ser nulo o estar vacío.");
        }

    }

}