package com.egg.Estancias.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egg.Estancias.entidades.Casa;
import com.egg.Estancias.entidades.Comentario;
import com.egg.Estancias.excepciones.MiExcepcion;
import com.egg.Estancias.repositorios.CasaRepositorio;
import com.egg.Estancias.repositorios.ComentarioRepositorio;

@Service
public class CasaServicio {

    @Autowired
    private CasaRepositorio casaRepositorio;

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;

    @Transactional
    public void crearCasa(String calle, int numero, String codigoPostal, String ciudad, String pais, Date fechaDesde,
            Date fechaHasta, int minDias, int maxDias, double precio, String tipoVivienda) throws MiExcepcion {
        
        validar(calle, numero, codigoPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda);
        
        Casa casa = new Casa();

        casa.setCalle(calle);
        casa.setNumero(numero);
        casa.setCodigoPostal(codigoPostal);
        casa.setCiudad(ciudad);
        casa.setPais(pais);
        casa.setFechaDesde(fechaDesde);
        casa.setFechaHasta(fechaHasta);
        casa.setMinDias(minDias);
        casa.setMaxDias(maxDias);
        casa.setPrecio(precio);
        casa.setTipoVivienda(tipoVivienda);

        casaRepositorio.save(casa);

    }

    @Transactional
    public void agregarComentario(String comentario, Integer idCasa) {

        Optional<Casa> respuesta = casaRepositorio.findById(idCasa);

        if (respuesta.isPresent()) {

            Casa casa = respuesta.get();

            Comentario c = new Comentario();
            c.setDescripcion(comentario);
            List<Comentario> lista = casa.getComentarios();
            lista.add(c);

            casaRepositorio.save(casa);
            comentarioRepositorio.save(c);

        }

    }

    public List<Casa> consultarCasas() {

        List<Casa> casas = new ArrayList<>();
        casas = casaRepositorio.findAll();

        return casas;
    }

    @Transactional
    public void eliminarCasa(Integer idCasa) {

        Optional<Casa> respuesta = casaRepositorio.findById(idCasa);

        if (respuesta.isPresent()) {
            casaRepositorio.deleteById(idCasa);
        }

    }

    @Transactional
    public void modificarCasa(Integer idCasa, String calle, int numero, String codigoPostal, String ciudad, String pais,
            Date fechaDesde, Date fechaHasta, int minDias, int maxDias, double precio, String tipoVivienda) throws MiExcepcion {

        validar(calle, numero, codigoPostal, ciudad, pais, fechaDesde, fechaHasta, minDias, maxDias, precio, tipoVivienda);

        Optional<Casa> respuesta = casaRepositorio.findById(idCasa);

        if (respuesta.isPresent()) {
            
            Casa casa = respuesta.get();

            casa.setCalle(calle);
            casa.setNumero(numero);
            casa.setCodigoPostal(codigoPostal);
            casa.setCiudad(ciudad);
            casa.setPais(pais);
            casa.setFechaDesde(fechaDesde);
            casa.setFechaHasta(fechaHasta);
            casa.setMinDias(minDias);
            casa.setMaxDias(maxDias);
            casa.setPrecio(precio);
            casa.setTipoVivienda(tipoVivienda);

            casaRepositorio.save(casa);
        }

    }

    public void validar(String calle, Integer numero, String codigoPostal, String ciudad, String pais, Date fechaDesde,
            Date fechaHasta, Integer minDias, Integer maxDias, Double precio, String tipoVivienda) throws MiExcepcion {

        if (calle.isEmpty() || calle == null) {
            throw new MiExcepcion("El campo 'Calle' no puede ser nulo o estar vacío.");
        }

        if (numero == null) {
            throw new MiExcepcion("El campo 'Número' no puede ser nulo o estar vacío.");
        }

        if (codigoPostal.isEmpty() || codigoPostal == null) {
            throw new MiExcepcion("El campo 'Código postal' no puede ser nulo o estar vacío.");
        }

        if (ciudad.isEmpty() || ciudad == null) {
            throw new MiExcepcion("El campo 'Ciudad' no puede ser nulo o estar vacío.");
        }

        if (pais.isEmpty() || pais == null) {
            throw new MiExcepcion("El campo 'País' no puede ser nulo o estar vacío.");
        }

        if (fechaDesde == null) {
            throw new MiExcepcion("El campo 'Fecha de inicio' no puede ser nulo o estar vacío.");
        }

        if (fechaHasta == null) {
            throw new MiExcepcion("El campo 'Fecha de finalización' no puede ser nulo o estar vacío.");
        }

        if (minDias == null) {
            throw new MiExcepcion("El campo 'Mínimo de días' no puede ser nulo o estar vacío.");
        }

        if (maxDias == null) {
            throw new MiExcepcion("El campo 'Máximo de días' no puede ser nulo o estar vacío.");
        }

        if (precio == null) {
            throw new MiExcepcion("El campo 'Precio' no puede ser nulo o estar vacío.");
        }

        if (tipoVivienda.isEmpty() || tipoVivienda == null) {
            throw new MiExcepcion("El campo 'Tipo de vivienda' no puede ser nulo o estar vacío.");
        }

    }

}
