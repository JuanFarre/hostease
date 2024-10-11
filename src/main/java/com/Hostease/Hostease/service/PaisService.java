package com.Hostease.Hostease.service;


import com.Hostease.Hostease.model.Pais;
import com.Hostease.Hostease.repository.IPaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService implements IPaisService {

    @Autowired
    private IPaisRepository paisRepository;

    @Override
    public Optional<Pais> findById(Long id) {
        return paisRepository.findById(id);
    }

    @Override
    public List<Pais> findAll() {
        return paisRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        paisRepository.deleteById(id);
    }

    @Override
    public Pais createPais(Pais pais) {
        return paisRepository.save(pais);
    }

    @Override
    public Pais editPais(Pais pais, Long id) {
        Optional<Pais> optionalPais = paisRepository.findById(id);

        if (optionalPais.isPresent()) {
            Pais existingPais = optionalPais.get();
            existingPais.setNombre(pais.getNombre());
            return paisRepository.save(existingPais);
        } else {
            throw new RuntimeException("País no encontrado con el ID: " + id);
        }
    }
}
