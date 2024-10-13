package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Ciudad;

import java.util.List;
import java.util.Optional;

public interface ICiudadService {

    public Optional<Ciudad> findById(Long id);

    public List<Ciudad> findAll();

    public void deleteById(Long id);

    public Ciudad createCiudad(Ciudad ciudad);

    public Ciudad editCiudad(Ciudad ciudad, Long id);
}
