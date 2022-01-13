package com.github.rguliamov.dreamtrip.app.model.entity.geography;

import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
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
    City city;
    Station station;
    TransportType testType = TransportType.AUTO;

    @BeforeEach
    void setup2() {
        city = new City("St. Petersburg");
    }

    @Test
    void testAddValidStationSuccess() {
        station = city.addStation(testType);
        assertTrue(city.getStations().contains(station));
    }

    @Test
    void testAddNullStationFail() {
        assertThrows(NullPointerException.class, () -> city.addStation(null));
    }

    @Test
    void testRemoveStationSuccess() {
        Station station = city.addStation(testType);
        city.removeStation(station);

        assertEquals(city.getStations().size(), 0);
    }

    @Test
    void testRemoveNullStationFail() {
        assertThrows(NullPointerException.class, () -> city.removeStation(null));
    }
}