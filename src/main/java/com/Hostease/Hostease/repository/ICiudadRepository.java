package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad, Long> {
}
