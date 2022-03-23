package com.github.rgulyamov.dreamtrip.app.service.impl;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.range.RangeCriteria;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * auto increment for entity id generation
     */
    private int incr = 0;

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
            city.setId(++incr);
            cities.add(city);
        }
    }

    @Override
    public Optional<City> findCityById(final int id) {
        return cities.stream()
                .filter(city -> city.getId() == id)
                .findFirst();
    }

    @Override
    public List<Station> searchStation(final StationCriteria stationCriteria, final RangeCriteria rangeCriteria) {
        //all cities matching the condition
        Stream<City> cityStream = cities.stream().filter(
                (city) -> StringUtils.isEmpty(stationCriteria.getCityName())
                        || city.getName().equals(stationCriteria.getCityName()));

        //get list of stations matching criteria
        Set<Station> stationSet = new HashSet<>();
        cityStream.map((city) -> city.getStations())
                .forEach((stations1) -> stationSet.addAll(stations1));

        if(stationSet.isEmpty())
            return Collections.emptyList();

        return stationSet.stream()
                .filter(station -> stationCriteria.getTransportType() == null
                        || station.getStationType().equals(stationCriteria.getTransportType()))
                .collect(Collectors.toList());
    }
}
