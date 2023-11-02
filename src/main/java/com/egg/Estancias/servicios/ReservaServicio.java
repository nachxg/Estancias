package com.egg.Estancias.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

//Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para
//realizar las reservas de viviendas (reservar, consultar reservas realizadas, modificación y
//eliminación).

import org.springframework.stereotype.Service;

import com.egg.Estancias.entidades.Casa;
import com.egg.Estancias.entidades.Estancia;
import com.egg.Estancias.excepciones.MiExcepcion;
import com.egg.Estancias.repositorios.CasaRepositorio;
import com.egg.Estancias.repositorios.EstanciaRepositorio;

@Service
public class ReservaServicio {
    
    @Autowired
    private EstanciaRepositorio estanciaRepositorio;

    @Autowired
    private CasaRepositorio casaRepositorio;

    @Transactional
    public void reservarEstancia(String huesped, Date fechaDesde, Date fechaHasta, Integer idCasa) throws MiExcepcion {

        validar(huesped, idCasa, fechaDesde, fechaHasta);

        Optional<Casa> respuesta = casaRepositorio.findById(idCasa);
        Estancia estancia = new Estancia();
        Casa casa = new Casa();

        if (respuesta.isPresent()) {
            casa = respuesta.get();
        }

        estancia.setCasa(casa);
        estancia.setHuesped(huesped);
        estancia.setFechaDesde(fechaDesde);
        estancia.setFechaHasta(fechaHasta);

        estanciaRepositorio.save(estancia);

    }

    public List<Estancia> consultarEstancias(){
        
        List<Estancia> estancias = new ArrayList<>();
        estancias = estanciaRepositorio.findAll();

        return estancias;
    }
    
    @Transactional
    public void eliminarEstancia(Integer idEstancia) throws MiExcepcion {

        Optional<Estancia> respuesta = estanciaRepositorio.findById(idEstancia);

        if (respuesta.isPresent()) {
            estanciaRepositorio.deleteById(idEstancia);
        }

    }

    @Transactional
    public void modificarEstancia(Integer idEstancia, String huesped, Date fechaDesde, Date fechaHasta, Integer idCasa) throws MiExcepcion {

        validar(huesped, idCasa, fechaDesde, fechaHasta);

        Optional<Estancia> respuestaEstancia = estanciaRepositorio.findById(idEstancia);
        Optional<Casa> respuestaCasa = casaRepositorio.findById(idCasa);

        Casa casa = new Casa();

        if (respuestaCasa.isPresent()) {
            casa = respuestaCasa.get();
        }

        if (respuestaEstancia.isPresent()) {
                    
            Estancia estancia = respuestaEstancia.get();

            estancia.setCasa(casa);
            estancia.setHuesped(huesped);
            estancia.setFechaDesde(fechaDesde);
            estancia.setFechaHasta(fechaHasta);

            estanciaRepositorio.save(estancia); 
        }

    }

    public void validar(String huesped, Integer idCasa, Date fechaDesde, Date fechaHasta) throws MiExcepcion {
        
        if(huesped.isEmpty() || huesped == null){
            throw new MiExcepcion("El campo 'Huésped' no puede ser nulo o estar vacío."); //
        }

        if(idCasa == null){
            throw new MiExcepcion("El campo 'Casa' no puede ser nulo o estar vacío.");
        }

        if(fechaDesde == null){
            throw new MiExcepcion("El campo 'Fecha de inicio' no puede ser nulo o estar vacío.");
        }

        if(fechaHasta == null){
            throw new MiExcepcion("El campo 'Fecha de finalización' no puede ser nulo o estar vacío.");
        }
        
    }

}
