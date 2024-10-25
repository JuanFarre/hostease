package com.Hostease.Hostease.service;

import com.Hostease.Hostease.dto.ServicioDTO;
import com.Hostease.Hostease.model.Servicio;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IServicioService {

    public Optional<Servicio> findById(Long id);

    public List<Servicio> findAll();

    public void deleteById(Long id);

    public Servicio createServicio(Servicio servicio);

    public Servicio editServicio(Servicio servicio, Long id);

    public ServicioDTO convertirADTO(Servicio servicio);

    public Servicio convertirAModelo(ServicioDTO dto);
}

