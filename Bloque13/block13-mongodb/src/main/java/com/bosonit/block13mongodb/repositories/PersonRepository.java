package com.bosonit.block13mongodb.repositories;

import com.bosonit.block13mongodb.domain.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person,String> {
    Optional<Person> findPersonByUser(String user);
    void deletePersonByUser(String user);
}
