package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.TipoHospedaje;
import com.Hostease.Hostease.repository.ITipoHospedajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoHospedajeService implements ITipoHospedajeService {

    @Autowired
    private ITipoHospedajeRepository tipoHospedajeRepository;

    @Override
    public Optional<TipoHospedaje> findById(Long id) {
        return tipoHospedajeRepository.findById(id);
    }

    @Override
    public List<TipoHospedaje> findAll() {
        return tipoHospedajeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        tipoHospedajeRepository.deleteById(id);
    }

    @Override
    public TipoHospedaje createTipoHospedaje(TipoHospedaje tipoHospedaje) {
        return tipoHospedajeRepository.save(tipoHospedaje);
    }

    @Override
    public TipoHospedaje editTipoHospedaje(TipoHospedaje tipoHospedaje, Long id) {
        Optional<TipoHospedaje> optionalTipoHospedaje = tipoHospedajeRepository.findById(id);

        if (optionalTipoHospedaje.isPresent()) {
            TipoHospedaje hospedaje = optionalTipoHospedaje.get();
            hospedaje.setNombre(tipoHospedaje.getNombre());
            return tipoHospedajeRepository.save(hospedaje);
        } else {
            throw new RuntimeException("Tipo de Hospedaje no encontrado con el ID: " + id);
        }
    }
}