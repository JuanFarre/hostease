package com.Hostease.Hostease.service;
import com.Hostease.Hostease.dto.ServicioDTO;
import com.Hostease.Hostease.model.Hospedaje;
import com.Hostease.Hostease.model.Servicio;
import com.Hostease.Hostease.model.TipoHospedaje;
import com.Hostease.Hostease.repository.IHospedajeRepository;
import com.Hostease.Hostease.repository.IServicioRepository;
import com.Hostease.Hostease.repository.ITipoHospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private IServicioRepository servicioRepository;

    @Autowired
    private IHospedajeRepository hospedajeRepository;



    @Override
    public Optional<Servicio> findById(Long id) {
        // Retorna el servicio encontrado por su ID
        return servicioRepository.findById(id);
    }



    @Override
    public List<Servicio> findAll() {
        // Retorna una lista de todos los servicios
        return servicioRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        // Elimina el servicio por su ID, si existe
        if (servicioRepository.existsById(id)) {
            servicioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Servicio no encontrado con ID: " + id);
        }
    }

    @Override
    public Servicio createServicio(Servicio servicio) {

        Set<Hospedaje> hospedajesCargados = new HashSet<>();

        for (Hospedaje hosped : servicio.getHospedajes()) {
            // Verificamos si el hospedaje existe en la base de datos
            Hospedaje hospedajeCompleto = hospedajeRepository.findById(hosped.getId())
                    .orElseThrow(() -> new RuntimeException("Hospedaje no encontrado con el ID " + hosped.getId()));

            // Asegúrate de que el tipo de hospedaje y otros atributos sean correctos
            if (hospedajeCompleto.getTipoHospedaje() == null) {
                throw new RuntimeException("El tipo de hospedaje para el ID " + hosped.getId() + " no está definido.");
            }

            hospedajesCargados.add(hospedajeCompleto);
        }

        servicio.setHospedajes(hospedajesCargados);
        return servicioRepository.save(servicio);
    }



    @Override
    public Servicio editServicio(Servicio servicio, Long id) {
        // Busca el servicio existente por su ID
        Servicio servicioExistente = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con el ID " + id));

        // Actualiza los campos del servicio existente con los nuevos valores
        servicioExistente.setNombre(servicio.getNombre()); // Actualiza el nombre u otros campos según sea necesario

        // Actualiza la lista de hospedajes, si es necesa
        // rio
        Set<Hospedaje> hospedajesCargados = new HashSet<>();
        for (Hospedaje hosped : servicio.getHospedajes()) {
            // Verificamos si el hospedaje existe en la base de datos
            Hospedaje hospedajeCompleto = hospedajeRepository.findById(hosped.getId())
                    .orElseThrow(() -> new RuntimeException("Hospedaje no encontrado con el ID " + hosped.getId()));

            // Asegúrate de que el tipo de hospedaje y otros atributos sean correctos
            if (hospedajeCompleto.getTipoHospedaje() == null) {
                throw new RuntimeException("El tipo de hospedaje para el ID " + hosped.getId() + " no está definido.");
            }

            hospedajesCargados.add(hospedajeCompleto);
        }

        // Establece los hospedajes cargados al servicio existente
        servicioExistente.setHospedajes(hospedajesCargados);

        // Guarda el servicio actualizado en la base de datos
        return servicioRepository.save(servicioExistente);
    }
    @Override
    public ServicioDTO convertirADTO(Servicio servicio) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId(servicio.getId());
        dto.setNombre(servicio.getNombre());
        return dto;
    }

    @Override
    public Servicio convertirAModelo(ServicioDTO dto) {
        Servicio servicio = new Servicio();
        servicio.setId(dto.getId());
        servicio.setNombre(dto.getNombre());
        return servicio;
    }






    }

