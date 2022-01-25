package com.github.rgulyamov.dreamtrip.app.service;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;

import java.util.List;

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
}
