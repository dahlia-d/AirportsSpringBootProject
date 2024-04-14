package com.databasesproject.airportspringbootproject.repositories;
import com.databasesproject.airportspringbootproject.models.Airport;
import org.springframework.data.repository.CrudRepository;

public interface AirportRepository  extends CrudRepository<Airport, Integer> {}
