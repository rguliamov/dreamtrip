package com.github.rguliamov.dreamtrip.app.repository;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;

import java.util.List;

/**
 * Define CRUD methods to access City objects in the persistence storage
 *
 * @author Guliamov Rustam
 */
public interface CityRepository {
    /**
     * Create or modified specified instance City
     *
     * @param city
     */
    void save(City city);

    /**
     * Return city with specified identifier. If no city exists with such id
     * then null is returned
     *
     * @param cityId
     * @return
     */
    City findById(int cityId);

    /**
     * Delete city with specified id
     *
     * @param cityId
     */
    void delete(int cityId);

    /**
     * Return all the cities
     *
     * @return
     */
    List<City> findAll();
}
