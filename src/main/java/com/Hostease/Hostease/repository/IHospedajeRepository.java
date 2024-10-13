package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.Hospedaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IHospedajeRepository extends JpaRepository<Hospedaje, Long> {


}
