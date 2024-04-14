package com.databasesproject.airportspringbootproject.repositories;

import com.databasesproject.airportspringbootproject.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
