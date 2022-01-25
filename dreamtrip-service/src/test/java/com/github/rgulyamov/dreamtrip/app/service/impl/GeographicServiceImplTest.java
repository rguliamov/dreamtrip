package com.github.rgulyamov.dreamtrip.app.service.impl.impl;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.service.GeographicService;
import com.github.rguliamov.dreamtrip.app.service.impl.GeographicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Guliamov Rustam
 *
 * contains unit-tests to check functionality of {@link GeographicServiceImpl} class
 */
class GeographicServiceImplTest {
    GeographicService geographicService;

    @BeforeEach
    void setup() {
        geographicService = new GeographicServiceImpl();
    }

    @Test
    void testNoDataReturnedAtStart() {
        List<City> cityList = geographicService.findCities();
        assertTrue(cityList.isEmpty());
    }

    @Test
    void testSaveNewCitySuccess() {
        City city = new City("St. Petersburg");
        geographicService.saveCity(city);

        assertEquals(geographicService.findCities().size(), 1);
        assertEquals(geographicService.findCities().get(0), city);
    }

    @Test
    void testSaveNullCityFail() {
        assertThrows(NullPointerException.class, () -> geographicService.saveCity(null));
    }
}