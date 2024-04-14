package com.databasesproject.airportspringbootproject.services;

import com.databasesproject.airportspringbootproject.models.Airport;
import com.databasesproject.airportspringbootproject.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAllAirports() {
        Iterable<Airport> airportIterable = airportRepository.findAll();
        List<Airport> airportList = new ArrayList<>();
        airportIterable.forEach(airportList::add);
        return airportList;
    }
}
