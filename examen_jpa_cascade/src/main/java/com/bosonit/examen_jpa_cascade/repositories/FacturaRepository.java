package com.bosonit.examen_jpa_cascade.repositories;

import com.bosonit.examen_jpa_cascade.domain.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura,Integer> {
}
