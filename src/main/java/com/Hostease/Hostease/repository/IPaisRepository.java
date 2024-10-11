package com.Hostease.Hostease.repository;

import com.Hostease.Hostease.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaisRepository extends JpaRepository<Pais, Long> {
}
