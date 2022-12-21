package com.bosonit.block10dockerizeapp.repositories;

import com.bosonit.block10dockerizeapp.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}
