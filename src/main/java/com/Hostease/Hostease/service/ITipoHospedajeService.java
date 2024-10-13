package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.TipoHospedaje;

import java.util.List;
import java.util.Optional;

public interface ITipoHospedajeService {

    public Optional<TipoHospedaje> findById(Long id);

    public List<TipoHospedaje> findAll();

    public void deleteById(Long id);

    public TipoHospedaje createTipoHospedaje(TipoHospedaje tipoHospedaje);

    public TipoHospedaje editTipoHospedaje(TipoHospedaje tipoHospedaje, Long id);
}