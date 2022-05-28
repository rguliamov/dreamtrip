package com.github.rgulyamov.dreamtrip.app.service.impl;

import com.github.rguliamov.dreamtrip.app.infra.util.CommonUtils;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.range.RangeCriteria;
import com.github.rguliamov.dreamtrip.app.repository.CityRepository;
import com.github.rguliamov.dreamtrip.app.repository.StationRepository;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Guliamov Rustam
 *
 * default implimentation of the {@link GeographicService} interface
 */
public class GeographicServiceImpl implements GeographicService {
    CityRepository cityRepository;

    StationRepository stationRepository;

    @Inject
    public GeographicServiceImpl(CityRepository cityRepository, StationRepository stationRepository) {
        this.cityRepository = cityRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public List<City> findCities() {
        return cityRepository.findAll();
    }

    @Override
    public void saveCity(City city) {
        Objects.requireNonNull(city, "city object not be null");
        cityRepository.save(city);
    }

    @Override
    public Optional<City> findCityById(final int id) {
        return Optional.ofNullable(cityRepository.findById(id));
    }

    @Override
    public List<Station> searchStation(final StationCriteria stationCriteria, final RangeCriteria rangeCriteria) {
        return CommonUtils.getSafeList(stationRepository.findAllByCriteria(stationCriteria));
    }
}
