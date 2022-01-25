package com.github.rgulyamov.dreamtrip.app.service.impl;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.service.GeographicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.github.rguliamov.dreamtrip.app.infra.util.CommonUtils.*;

/**
 * @author Guliamov Rustam
 *
 * default implimentation of the {@link GeographicService} interface
 */
public class GeographicServiceImpl implements GeographicService {
    /**
     * List of cities
     */
    private List<City> cities;

    public GeographicServiceImpl() {
        cities = new ArrayList<>();
    }

    @Override
    public List<City> findCities() {
        return getSafeList(cities);
    }

    @Override
    public void saveCity(City city) {
        Objects.requireNonNull(city, "city object not be null");
        if(!cities.contains(city)) {
            cities.add(city);
        }
    }
}
