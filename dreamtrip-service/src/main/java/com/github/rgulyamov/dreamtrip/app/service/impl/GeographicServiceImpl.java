package com.github.rgulyamov.dreamtrip.app.service.impl;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.range.RangeCriteria;
import com.github.rguliamov.dreamtrip.app.repository.CityRepository;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Guliamov Rustam
 *
 * default implimentation of the {@link GeographicService} interface
 */
public class GeographicServiceImpl implements GeographicService {
    CityRepository repository;

    @Inject
    public GeographicServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<City> findCities() {
        return repository.findAll();
    }

    @Override
    public void saveCity(City city) {
        Objects.requireNonNull(city, "city object not be null");
        repository.save(city);
    }

    @Override
    public Optional<City> findCityById(final int id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public List<Station> searchStation(final StationCriteria stationCriteria, final RangeCriteria rangeCriteria) {
        Set<Station> stationSet = new HashSet<>();

        return repository.findAll().stream()
                .flatMap(city -> city.getStations().stream())
                .filter(station -> station.match(stationCriteria))
                .collect(Collectors.toList());
    }
}
