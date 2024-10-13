package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.TipoHospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoHospedajeRepository extends JpaRepository<TipoHospedaje, Long> {
}
