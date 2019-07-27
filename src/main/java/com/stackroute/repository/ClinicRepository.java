package com.stackroute.repository;

import com.stackroute.domain.Clinic;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends Neo4jRepository<Clinic, String> {
}
