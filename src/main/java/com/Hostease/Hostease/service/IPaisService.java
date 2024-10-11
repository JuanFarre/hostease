package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Pais;

import java.util.List;
import java.util.Optional;

public interface IPaisService {

    public Optional<Pais> findById(Long id);

    public List<Pais> findAll();

    public void deleteById(Long id);

    public Pais createPais(Pais pais);

    public Pais editPais(Pais pais, Long id);
}
