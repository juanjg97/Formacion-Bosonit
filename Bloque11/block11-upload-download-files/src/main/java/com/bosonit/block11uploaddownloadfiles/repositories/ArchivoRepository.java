package com.bosonit.block11uploaddownloadfiles.repositories;

import com.bosonit.block11uploaddownloadfiles.domain.entities.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo,Integer> {
}
