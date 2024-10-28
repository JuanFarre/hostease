package com.Hostease.Hostease.service;

import com.Hostease.Hostease.dto.ServicioDTO;
import com.Hostease.Hostease.model.Servicio;
import com.Hostease.Hostease.repository.IServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private IServicioRepository servicioRepository;

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
        // Crea y guarda un nuevo servicio
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio editServicio(Servicio servicio, Long id) {
        // Busca el servicio existente por su ID
        Servicio servicioExistente = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con el ID " + id));

        // Actualiza los campos del servicio existente con los nuevos valores
        servicioExistente.setNombre(servicio.getNombre()); // Actualiza el nombre u otros campos según sea necesario

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