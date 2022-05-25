package com.github.rguliamov.dreamtrip.app.repository.inmemory;

import com.github.rguliamov.dreamtrip.app.infra.util.CommonUtils;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory implementation of the {@link CityRepository}
 *
 * @author Guliamov Rustam
 */
public class InMemoryCityRepository implements CityRepository {
    /**
     * internal list of cities
     */
    private final List<City> cities = new ArrayList<>();

    /**
     * Auto-increment counter for entity id generation
     */
    private int cityCounter = 0;

    private int cityStationCounter = 0;

    @Override
    public void save(City city) {
        if(!cities.contains(city)) {
            cities.add(city);
            city.setId(++cityCounter);
        }
        city.getStations().forEach(s -> {
            if(s.getId() == 0)
                s.setId(++cityStationCounter);
        });
    }

    @Override
    public City findById(int cityId) {
        return cities.stream().filter(city -> city.getId() == cityId).findFirst().orElse(null);
    }

    @Override
    public void delete(int cityId) {
    }

    @Override
    public List<City> findAll() {
        return CommonUtils.getSafeList(cities);
    }
}
