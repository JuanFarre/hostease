package com.Hostease.Hostease.service;


import com.Hostease.Hostease.model.Ciudad;
import com.Hostease.Hostease.model.Pais;
import com.Hostease.Hostease.repository.ICiudadRepository;
import com.Hostease.Hostease.repository.IPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadService implements ICiudadService {

    @Autowired
    private ICiudadRepository ciudadRepository;

    @Autowired
    private IPaisRepository paisRepository;

    @Override
    public Optional<Ciudad> findById(Long id) {
        return ciudadRepository.findById(id);
    }

    @Override
    public List<Ciudad> findAll() {
        return ciudadRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        ciudadRepository.deleteById(id);
    }

    @Override
    public Ciudad createCiudad(Ciudad ciudad) {
        Optional<Pais> pais = paisRepository.findById(ciudad.getPais().getId());

        if(pais.isPresent()){
            ciudad.setPais(pais.get());
        } else {
            throw new RuntimeException("País no encontrado");
        }

        return ciudadRepository.save(ciudad);
    }

    @Override
    public Ciudad editCiudad(Ciudad ciudad, Long id) {
        Optional<Ciudad> optionalCiudad = ciudadRepository.findById(id);

        if (optionalCiudad.isPresent()) {
            Ciudad existingCiudad = optionalCiudad.get();

            // Actualizar el nombre de la ciudad
            existingCiudad.setNombre(ciudad.getNombre());

            // Si hay un país asociado en el objeto recibido, lo buscamos en la base de datos
            Optional<Pais> paisOpt = paisRepository.findById(ciudad.getPais().getId());
            if (paisOpt.isPresent()) {
                // Si el país existe, lo asignamos a la ciudad
                existingCiudad.setPais(paisOpt.get());
            } else {
                throw new RuntimeException("País no encontrado con el ID: " + ciudad.getPais().getId());
            }

            // Guardar los cambios en la ciudad
            return ciudadRepository.save(existingCiudad);
        } else {
            throw new RuntimeException("Ciudad no encontrada con el ID: " + id);
        }
    }

}

