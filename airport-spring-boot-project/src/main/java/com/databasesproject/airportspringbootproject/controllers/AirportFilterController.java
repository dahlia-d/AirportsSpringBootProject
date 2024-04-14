package com.databasesproject.airportspringbootproject.controllers;

import com.databasesproject.airportspringbootproject.models.AirportFilter;
import com.databasesproject.airportspringbootproject.services.AirportFilterService;
import com.databasesproject.airportspringbootproject.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirportFilterController {

    private final AirportFilterService airportFilterService;
    private final AirportService airportService;

    @Autowired
    public AirportFilterController(AirportFilterService airportFilterService, AirportService airportService) {
        this.airportFilterService = airportFilterService;
        this.airportService = airportService;
    }

    @PostMapping("/api/v0/airports/filter")
    public Object filterAirports(
            @RequestParam boolean countryAsRoot,
            @RequestBody AirportFilter airportFilter
    ) {
        if (countryAsRoot) {
            return airportFilterService.filterWithCountryAsRoot(airportFilter);
        } else {
            return airportFilterService.filterWithAirportAsRoot(airportFilter);
        }
    }

}
