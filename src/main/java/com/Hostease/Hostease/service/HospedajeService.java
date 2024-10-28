package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Hospedaje;
import com.Hostease.Hostease.repository.ICiudadRepository;
import com.Hostease.Hostease.repository.ITipoHospedajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.Hostease.Hostease.repository.IHospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HospedajeService implements IHospedajeService {

    @Autowired
    private IHospedajeRepository hospedajeRepository;

    @Autowired
    private ITipoHospedajeRepository tipoHospedajeRepository;

    @Autowired
    private ICiudadRepository ciudadRepository;

    @Override
    public Optional<Hospedaje> findById(Long id) {
        return hospedajeRepository.findById(id);
    }

    @Override
    public List<Hospedaje> findAll() {
        return hospedajeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        hospedajeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Hospedaje createHospedaje(Hospedaje hospedaje) {
        return hospedajeRepository.save(hospedaje);
    }

    @Override
    @Transactional
    public Hospedaje editHospedaje(Hospedaje hospedaje, Long id) {
        Hospedaje existingHospedaje = hospedajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospedaje no encontrado con ID: " + id));

        existingHospedaje.setDescripcion(hospedaje.getDescripcion());
        existingHospedaje.setPrecioPorNoche(hospedaje.getPrecioPorNoche());
        existingHospedaje.setImagen(hospedaje.getImagen());
        existingHospedaje.setTipoHospedaje(hospedaje.getTipoHospedaje());
        existingHospedaje.setCiudad(hospedaje.getCiudad());
        existingHospedaje.setFechaModificacion(hospedaje.getFechaModificacion());

        // Actualiza los servicios
        existingHospedaje.setServicios(hospedaje.getServicios());

        // Guardar los cambios
        return hospedajeRepository.save(existingHospedaje);
    }
}