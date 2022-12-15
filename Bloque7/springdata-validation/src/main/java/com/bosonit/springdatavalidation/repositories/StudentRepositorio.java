package com.bosonit.springdatavalidation.repositories;

import com.bosonit.springdatavalidation.domain.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositorio extends JpaRepository<Student,Integer> {
}
