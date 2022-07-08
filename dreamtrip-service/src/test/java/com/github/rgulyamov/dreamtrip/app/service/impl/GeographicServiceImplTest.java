package com.github.rgulyamov.dreamtrip.app.service.impl;

import com.github.rguliamov.dreamtrip.app.hibernate.SessionFactoryBuilder;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Address;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.range.RangeCriteria;
import com.github.rguliamov.dreamtrip.app.repository.CityRepository;
import com.github.rguliamov.dreamtrip.app.repository.StationRepository;
import com.github.rguliamov.dreamtrip.app.repository.hibernate.HibernateCityRepository;
import com.github.rguliamov.dreamtrip.app.repository.hibernate.HibernateStationRepository;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Guliamov Rustam
 * <p>
 * contains unit-tests to check functionality of {@link GeographicServiceImpl} class
 */
class GeographicServiceImplTest {
    GeographicService geographicService;
    StationRepository stationRepository;
    CityRepository cityRepository;
    SessionFactoryBuilder builder;
    ExecutorService executorService;

    @BeforeEach
    void setup() {
        builder = new SessionFactoryBuilder();
        cityRepository = new HibernateCityRepository(builder);
        stationRepository = new HibernateStationRepository(builder);
        geographicService = new GeographicServiceImpl(cityRepository, stationRepository);
        executorService = Executors.newCachedThreadPool();
    }

    @Test
    void testNoDataReturnedAtStart() {
        List<City> cityList = geographicService.findCities();
        assertTrue(cityList.isEmpty());
    }

    @Test
    void testSaveNewCitySuccess() {
        City city = new City("St. Petersburg");
        city.setRegion("None");
        city.setDistrict("None");
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
        city1.setDistrict("None");
        city1.setRegion("None");
        City city2 = new City("Chelyabinsk");
        city2.setDistrict("None");
        city2.setRegion("None");

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);

        Optional<City> answer1 = geographicService.findCityById(1);
        Optional<City> answer2 = geographicService.findCityById(2);
        assertEquals(answer1.get().getName(), "Magnitogorsk" );
        assertEquals(answer2.get().getName(), "Chelyabinsk");
    }

    @Test
    void testSearchCityByIdNotFoundReturnEmptyOptional() {
        City city = new City("Magnitogorsk");
        city.setRegion("None");
        city.setDistrict("None");
        geographicService.saveCity(city);

        Optional<City> answer = geographicService.findCityById(2);
        assertTrue(answer.isEmpty());
    }

    @Test
    void testSearchStationsByCitySuccess() {
        StationCriteria criteria = new StationCriteria("Magnitogorsk");

        //получаем список городов со станциями
        List<City> cities = setupCityAndStation();

        //сохраняем города в БД
        cities.forEach(city -> geographicService.saveCity(city));

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));

        assertEquals(stationList.size(), 4);
    }

    @Test
    void testSearchStationByCityNotFoundReturnEmptyList() {
        StationCriteria criteria = new StationCriteria("Magnitogorsk");

        City city1 = new City("Magnitogorsk");
        city1.setDistrict("none");
        city1.setRegion("none");
        City city2 = new City("Chelyabinsk");
        city2.setDistrict("none");
        city2.setRegion("none");

        Station station5 = new Station(city2, TransportType.RAILWAY);
        Station station6 = new Station(city2, TransportType.AVIA);

        city2.addStation(station5);
        city2.addStation(station6);

        geographicService.saveCity(city1);
        geographicService.saveCity(city2);

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0,0));
        assertTrue(stationList.isEmpty());
    }

    @Test
    void testSearchStationByTransportTypeSuccess() {
        StationCriteria criteria = new StationCriteria();
        criteria.setTransportType(TransportType.AVIA);

        List<City> cities = setupCityAndStation();

        cities.forEach(city -> geographicService.saveCity(city));

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));

        assertEquals(stationList.size(), 2);
    }

    @Test
    void testSearchStationByTransportTypeNotFoundReturnEmptyList() {
        StationCriteria criteria = new StationCriteria();
        criteria.setTransportType(TransportType.AVIA);

        City city = new City("St. Petersburg");
        city.setDistrict("None");
        city.setRegion("None");
        city.addStation(new Station(city, TransportType.AUTO));
        city.addStation(new Station(city, TransportType.RAILWAY));

        geographicService.saveCity(city);

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));
        assertTrue(stationList.isEmpty());
    }

    @Test
    void testSearchStationByEmptyCriteriaReturnAllStations() {
        StationCriteria criteria = new StationCriteria();

        List<City> cities = setupCityAndStation();

        cities.forEach(city -> geographicService.saveCity(city));

        List<Station> stationList = geographicService.searchStation(criteria, new RangeCriteria(0, 0));

        stationList.forEach(s -> System.out.println(s.getAddress()));

        assertEquals(stationList.size(), 6);
    }

    @Test
    void testSaveMultipleCitiesSuccess() {
        int cityCount = geographicService.findCities().size();

        int addedCount = 100_000;
        for (int i = 0; i < addedCount; i++) {
            City city = new City("Petersburg" + i);
            city.setRegion("None");
            city.setDistrict("None");
            city.addStation(TransportType.AVIA);

            geographicService.saveCity(city);
        }

        int size = geographicService.findCities().size();

        assertEquals(cityCount + addedCount, size);
    }

    @Test
    void testSaveMultipleCitiesConcurrentlySuccess() {
        int cityCount = geographicService.findCities().size();

        int threadCount = 100;
        int batchCount = 10;

        List<Future<?>> futures = new ArrayList<>();

        for(int i = 0; i < threadCount; i++) {
            futures.add(executorService.submit(() -> {
                for (int j = 0; j < batchCount; j++) {
                    City city = new City(ThreadLocalRandom.current().nextInt(10000) + "St. Petersburg" + ThreadLocalRandom.current().nextInt(10000));
                    city.setDistrict("None");
                    city.setRegion("None");
                    city.addStation(TransportType.AVIA);
                    geographicService.saveCity(city);
                }
            }));
        }

        waitForFutures(futures);

        assertEquals(geographicService.findCities().size(), cityCount + threadCount * batchCount);
    }




    //for tests
    public void waitForFutures(List<Future<?>> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        });
    }

    public List<City> setupCityAndStation() {
        City city1 = new City("Magnitogorsk");
        city1.setRegion("None");
        city1.setDistrict("None");
        City city2 = new City("Chelyabinsk");
        city2.setRegion("None");
        city2.setDistrict("None");

        Station station1 = new Station(city1, TransportType.RAILWAY);
        Address address1 = new Address();
        address1.setStreet("1");
        station1.setAddress(address1);

        Station station2 = new Station(city1, TransportType.AUTO);
        Address address2 = new Address();
        address2.setStreet("2");
        station2.setAddress(address2);

        Station station3 = new Station(city1, TransportType.AUTO);
        Address address3 = new Address();
        address3.setStreet("3");
        station3.setAddress(address3);

        Station station4 = new Station(city1, TransportType.AVIA);
        Address address4 = new Address();
        address4.setStreet("4");
        station4.setAddress(address4);

        Station station5 = new Station(city2, TransportType.RAILWAY);
        Address address5 = new Address();
        address5.setStreet("5");
        station5.setAddress(address5);

        Station station6 = new Station(city2, TransportType.AVIA);
        Address address6 = new Address();
        address6.setStreet("6");
        station6.setAddress(address6);

        city1.addStation(station1);
        city1.addStation(station2);
        city1.addStation(station3);
        city1.addStation(station4);
        city2.addStation(station5);
        city2.addStation(station6);

        return List.of(city1, city2);
    }
}