package com.Hostease.Hostease.service;

import com.Hostease.Hostease.model.Hospedaje;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IHospedajeService {

    public Optional<Hospedaje> findById(Long id);

    public List<Hospedaje> findAll();

    public void deleteById(Long id);

    public Hospedaje createHospedaje(Hospedaje hospedaje);

    public Hospedaje editHospedaje(Hospedaje hospedaje, Long id);


}

