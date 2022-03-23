package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains unit-tests to check functionality of {@link Station} class
 *
 * @author Gulyamov Rustam
 */
class StationTest {
    static StationCriteria criteriaByCity;
    static StationCriteria criteriaByType;
    static StationCriteria emptyCriteria;
    static City city;

    @BeforeAll
    static void setup() {
        city = new City("Magnitogorsk");
        criteriaByCity = new StationCriteria("Magnitogorsk");
        criteriaByType = new StationCriteria(TransportType.AVIA);
        emptyCriteria = new StationCriteria();
    }

    @Test
    void testMatchByEmptyCriteriaTrueAlways() {
        Station station = new Station(city, TransportType.AVIA);
        assertTrue(station.match(emptyCriteria));
    }

    @Test
    void testMatchByCityTrue() {
        Station station = new Station(city, TransportType.RAILWAY);
        assertTrue(station.match(criteriaByCity));
    }

    @Test
    void testMatchByCityFalse() {
        Station station = new Station(new City("Chelyabinsk"), TransportType.RAILWAY);
        assertFalse(station.match(criteriaByCity));
    }

    @Test
    void testMatchByTypeTrue() {
        Station station = new Station(city, TransportType.AVIA);
        assertTrue(station.match(criteriaByType));
    }

    @Test
    void testMatchByTypeFalse() {
        Station station = new Station(city, TransportType.RAILWAY);
        assertFalse(station.match(criteriaByType));
    }
}