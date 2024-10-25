package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Hospedaje;
import com.Hostease.Hostease.repository.ICiudadRepository;
import com.Hostease.Hostease.repository.ITipoHospedajeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Hospedaje createHospedaje(Hospedaje hospedaje) {
        return hospedajeRepository.save(hospedaje);
    }

    @Override
    public Hospedaje editHospedaje(Hospedaje hospedaje, Long id) {
        Optional<Hospedaje> optionalHospedaje = hospedajeRepository.findById(id);

        if (optionalHospedaje.isPresent()) {
            Hospedaje hospedajeExistente = optionalHospedaje.get();

            // Actualizar los campos del hospedaje existente
            hospedajeExistente.setDescripcion(hospedaje.getDescripcion());
            hospedajeExistente.setImagen(hospedaje.getImagen());
            hospedajeExistente.setPrecioPorNoche(hospedaje.getPrecioPorNoche());
            hospedajeExistente.setFechaModificacion(LocalDateTime.now());

            // Si se quiere cambiar el tipo de hospedaje y la ciudad
            if (hospedaje.getTipoHospedaje() != null) {
                hospedajeExistente.setTipoHospedaje(hospedaje.getTipoHospedaje());
            }
            if (hospedaje.getCiudad() != null) {
                hospedajeExistente.setCiudad(hospedaje.getCiudad());
            }

            return hospedajeRepository.save(hospedajeExistente);
        } else {
            throw new RuntimeException("Hospedaje no encontrado con el ID: " + id);
        }
    }



}

