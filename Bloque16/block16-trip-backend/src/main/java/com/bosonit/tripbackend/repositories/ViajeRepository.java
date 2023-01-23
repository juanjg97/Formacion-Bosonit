package com.bosonit.tripbackend.repositories;

import com.bosonit.tripbackend.domain.entities.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje,Integer> {
}
