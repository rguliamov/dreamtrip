package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gulyamov Rustam
 *
 * contatins unit-tests to check functionalty of {@link City} class
 */
class CityTest {
    static Station station;
    static City city;

    @BeforeAll
    static void setup1() {
        station = new Station();
    }

    @BeforeEach
    void setup2() {
        city = new City();
    }

    @Test
    void addValidStationSuccess() {
        city.addStation(station);
        assertTrue(city.getStations().contains(station));
    }

    @Test
    void addNullStationFail() {
        assertThrows(NullPointerException.class, () -> city.addStation(null));
    }
}