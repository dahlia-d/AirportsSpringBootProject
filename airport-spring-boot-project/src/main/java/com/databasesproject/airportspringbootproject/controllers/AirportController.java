package com.databasesproject.airportspringbootproject.controllers;

import com.databasesproject.airportspringbootproject.services.AirportService;
import com.databasesproject.airportspringbootproject.models.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v0/airports")
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportResponse> getAllAirports() {
        List<Airport> airports = airportService.getAllAirports();
        return airports.stream().map(this::mapToAirportResponse).collect(Collectors.toList());
    }

    private AirportResponse mapToAirportResponse(Airport airport) {
        AirportResponse response = new AirportResponse();
        response.setId(airport.getId());
        response.setName(airport.getName());
        response.setIataCode(airport.getIataCode());
        response.setIcaoCode(airport.getIcaoCode());
        response.setLatitude(airport.getLatitude());
        response.setLongitude(airport.getLongitude());

        AirportResponse.City city = new AirportResponse.City();
        city.setId(airport.getCity().getId());
        city.setName(airport.getCity().getName());

        AirportResponse.Country country = new AirportResponse.Country();
        country.setName(airport.getCity().getCountry().getName());
        country.setIso2CountryCode(airport.getCity().getCountry().getIso2CountryCode());
        country.setIso3CountryCode(airport.getCity().getCountry().getIso3CountryCode());

        city.setCountry(country);
        response.setCity(city);

        return response;
    }

    static class AirportResponse {
        private Long id;
        private String name;
        private City city;
        private String iataCode;
        private String icaoCode;
        private Double latitude;
        private Double longitude;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public String getIataCode() {
            return iataCode;
        }

        public void setIataCode(String iataCode) {
            this.iataCode = iataCode;
        }

        public String getIcaoCode() {
            return icaoCode;
        }

        public void setIcaoCode(String icaoCode) {
            this.icaoCode = icaoCode;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        static class City {
            private Long id;
            private String name;
            private Country country;


            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Country getCountry() {
                return country;
            }

            public void setCountry(Country country) {
                this.country = country;
            }
        }

        static class Country {
            private String name;
            private String iso2CountryCode;
            private String iso3CountryCode;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIso2CountryCode() {
                return iso2CountryCode;
            }

            public void setIso2CountryCode(String iso2CountryCode) {
                this.iso2CountryCode = iso2CountryCode;
            }

            public String getIso3CountryCode() {
                return iso3CountryCode;
            }

            public void setIso3CountryCode(String iso3CountryCode) {
                this.iso3CountryCode = iso3CountryCode;
            }
        }
    }

}
