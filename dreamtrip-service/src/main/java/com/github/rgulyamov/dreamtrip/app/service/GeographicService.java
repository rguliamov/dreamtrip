package com.github.rgulyamov.dreamtrip.app.service;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.range.RangeCriteria;

import java.util.List;
import java.util.Optional;

/**
 * @author Guliamov Rustam
 *
 * Entry point to perform operations
 * over geographic entities
 */
public interface GeographicService {
    /**
     * return list of existing cities
     *
     * @return
     */
    List<City> findCities();

    /**
     * safe specify city instance
     *
     * @param city
     */
    void saveCity(City city);

    /**
     * Returns city with specified id. If no city is found then empty optional is returned
     *
     * @param id
     * @return
     */
    Optional<City> findCityById(int id);

    /**
     * Returns all the stations that match specified criteria
     *
     * @param stationCriteria
     * @param rangeCriteria
     * @return
     */
    List<Station> searchStation(StationCriteria stationCriteria, RangeCriteria rangeCriteria);
}
