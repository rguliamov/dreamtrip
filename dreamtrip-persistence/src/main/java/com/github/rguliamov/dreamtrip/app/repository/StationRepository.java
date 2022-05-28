package com.github.rguliamov.dreamtrip.app.repository;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;

import java.util.List;

/**
 * Define CRUD methods to access Station objects in the persistence storage
 *
 * @author Guliamov Rustam
 */
public interface StationRepository {
    /**
     * Returns all the stations that match specified criteria
     *
     * @param criteria
     * @return
     */
    List<Station> findAllByCriteria(StationCriteria criteria);
}
