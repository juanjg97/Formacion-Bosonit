package com.bosonit.examen_jpa_cascade.repositories;

import com.bosonit.examen_jpa_cascade.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
