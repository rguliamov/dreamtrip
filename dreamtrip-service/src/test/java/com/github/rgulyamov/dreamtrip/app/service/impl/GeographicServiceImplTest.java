package com.github.rgulyamov.dreamtrip.app.service.impl;

import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.range.RangeCriteria;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Guliamov Rustam
 * <p>
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

    @Test
    void testSearchCityByIdSuccess() {
        City city1 = new City("Magnitogorsk");
        City city2 = new City("Chelyabinsk");

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);

        Optional<City> answer1 = geographicService.findCityById(1);
        Optional<City> answer2 = geographicService.findCityById(2);
        assertEquals(answer1.get().getName(), "Magnitogorsk");
        assertEquals(answer2.get().getName(), "Chelyabinsk");
    }

    @Test
    void testSearchCityByIdNotFoundReturnEmptyOptional() {
        City city = new City("Magnitogorsk");
        geographicService.saveCity(city);

        Optional<City> answer = geographicService.findCityById(2);
        assertTrue(answer.isEmpty());
    }

    @Test
    void testSearchStationsByCitySuccess() {
        StationCriteria criteria = new StationCriteria("Magnitogorsk");

        City city1 = new City("Magnitogorsk");
        City city2 = new City("Chelyabinsk");

        Station station1 = new Station(city1, TransportType.RAILWAY);
        Station station2 = new Station(city1, TransportType.AUTO);
        Station station3 = new Station(city1, TransportType.AUTO);
        Station station4 = new Station(city1, TransportType.AVIA);
        Station station5 = new Station(city2, TransportType.RAILWAY);
        Station station6 = new Station(city2, TransportType.AVIA);

        city1.addStation(station1).setId(1);
        city1.addStation(station2).setId(2);
        city1.addStation(station3).setId(3);
        city1.addStation(station4).setId(4);
        city2.addStation(station5).setId(5);
        city2.addStation(station6).setId(6);

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));
        assertTrue(stationList.contains(station1));
        assertTrue(stationList.contains(station2));
        assertTrue(stationList.contains(station3));
        assertTrue(stationList.contains(station4));
        assertEquals(stationList.size(), 4);
    }

    @Test
    void testSearchStationByCityNotFoundReturnEmptyList() {
        StationCriteria criteria = new StationCriteria("Magnitogorsk");

        City city1 = new City("Magnitogorsk");
        City city2 = new City("Chelyabinsk");

        Station station5 = new Station(city2, TransportType.RAILWAY);
        Station station6 = new Station(city2, TransportType.AVIA);

        city2.addStation(station5).setId(5);
        city2.addStation(station6).setId(6);

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0,0));
        assertTrue(stationList.isEmpty());
    }

    @Test
    void testSearchStationByTransportTypeSuccess() {
        StationCriteria criteria = new StationCriteria();
        criteria.setTransportType(TransportType.AVIA);

        City city1 = new City("Magnitogorsk");
        City city2 = new City("Chelyabinsk");
        City city3 = new City("St. Petersburg");

        Station station1 = new Station(city1, TransportType.RAILWAY);
        Station station2 = new Station(city1, TransportType.AUTO);
        Station station3 = new Station(city1, TransportType.AUTO);
        Station station4 = new Station(city1, TransportType.AVIA);
        Station station5 = new Station(city2, TransportType.RAILWAY);
        Station station6 = new Station(city2, TransportType.AVIA);

        city1.addStation(station1).setId(1);
        city1.addStation(station2).setId(2);
        city2.addStation(station3).setId(3);
        city2.addStation(station4).setId(4);
        city3.addStation(station5).setId(5);
        city3.addStation(station6).setId(6);

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);
        geographicService.saveCity(city3);

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));

        assertEquals(stationList.size(), 2);
        assertTrue(stationList.contains(station4));
        assertTrue(stationList.contains(station6));
    }

    @Test
    void testSearchStationByTransportTypeNotFoundReturnEmptyList() {
        StationCriteria criteria = new StationCriteria();
        criteria.setTransportType(TransportType.AVIA);

        City city1 = new City("Magnitogorsk");
        City city2 = new City("Chelyabinsk");
        City city3 = new City("St. Petersburg");

        Station station1 = new Station(city1, TransportType.RAILWAY);
        Station station2 = new Station(city1, TransportType.AUTO);
        Station station3 = new Station(city1, TransportType.AUTO);
        Station station5 = new Station(city2, TransportType.RAILWAY);

        city1.addStation(station1).setId(1);
        city1.addStation(station2).setId(2);
        city2.addStation(station3).setId(3);
        city3.addStation(station5).setId(5);

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);
        geographicService.saveCity(city3);

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));
        assertTrue(stationList.isEmpty());
    }

    @Test
    void testSearchStationByEmptyCriteriaReturnAllStations() {
        StationCriteria criteria = new StationCriteria();

        City city1 = new City("Magnitogorsk");
        City city2 = new City("Chelyabinsk");
        City city3 = new City("St. Petersburg");

        Station station1 = new Station(city1, TransportType.RAILWAY);
        Station station2 = new Station(city1, TransportType.AUTO);
        Station station3 = new Station(city1, TransportType.AUTO);
        Station station4 = new Station(city1, TransportType.AVIA);
        Station station5 = new Station(city2, TransportType.RAILWAY);
        Station station6 = new Station(city2, TransportType.AVIA);

        city1.addStation(station1).setId(1);
        city1.addStation(station2).setId(2);
        city2.addStation(station3).setId(3);
        city2.addStation(station4).setId(4);
        city3.addStation(station5).setId(5);
        city3.addStation(station6).setId(6);

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);
        geographicService.saveCity(city3);

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));

        assertEquals(stationList.size(), 6);
    }
}