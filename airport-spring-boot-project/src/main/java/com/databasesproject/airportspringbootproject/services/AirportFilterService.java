package com.databasesproject.airportspringbootproject.services;

import com.databasesproject.airportspringbootproject.models.AirportFilter;
import com.databasesproject.airportspringbootproject.models.Airport;
import com.databasesproject.airportspringbootproject.models.City;
import com.databasesproject.airportspringbootproject.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportFilterService {

    private final AirportService airportService;
    private final CountryService countryService;

    @Autowired
    public AirportFilterService(AirportService airportService, CountryService countryService) {
        this.airportService = airportService;
        this.countryService = countryService;
    }

    public Object filterWithCountryAsRoot(AirportFilter airportFilter) {
        List<Country> filteredCountries = countryService.getAllCountries().stream()
                .filter(country -> airportFilter.countryIso2Codes().isEmpty() || airportFilter.countryIso2Codes().contains(country.getIso2CountryCode()))
                .collect(Collectors.toList());

        for (Country country : filteredCountries) {
            country.setCities(country.getCities().stream()
                    .filter(city -> airportFilter.cityIds().isEmpty() || airportFilter.cityIds().contains(city.getId()))
                    .collect(Collectors.toSet()));

            for (City city : country.getCities()) {
                city.setAirports(city.getAirports().stream()
                        .filter(airport -> airportFilter.airportIcaoCodes().isEmpty() || airportFilter.airportIcaoCodes().contains(airport.getIcaoCode()))
                        .filter(airport -> airportFilter.airportNames().isEmpty() || airportFilter.airportNames().contains(airport.getName()))
                        .collect(Collectors.toSet()));
            }
        }

        return filteredCountries;
    }

    public Object filterWithAirportAsRoot(AirportFilter airportFilter) {
        List<Airport> filteredAirports = airportService.getAllAirports().stream()
                .filter(airport -> airportFilter.airportIcaoCodes().isEmpty() || airportFilter.airportIcaoCodes().contains(airport.getIcaoCode()))
                .filter(airport -> airportFilter.airportNames().isEmpty() || airportFilter.airportNames().contains(airport.getName()))
                .collect(Collectors.toList());

        for (Airport airport : filteredAirports) {
            airport.getCity().setCountry(airport.getCity().getCountry());
        }

        return filteredAirports;
    }
}
