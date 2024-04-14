package com.databasesproject.airportspringbootproject.services;

import com.databasesproject.airportspringbootproject.models.Country;
import com.databasesproject.airportspringbootproject.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return (List<Country>) countryRepository.findAll();
    }
}
