package com.github.rguliamov.dreamtrip.app.repository.hibernate;

import com.github.rguliamov.dreamtrip.app.hibernate.SessionFactoryBuilder;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.transport.TransportType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HibernateCityRepositoryTest {
    HibernateCityRepository repository;

    @BeforeEach
    public void setup() {
        repository = new HibernateCityRepository(new SessionFactoryBuilder());
    }

    @Test
    void testSaveCitySuccess() {
        City city = new City("Magnitogorsk");
        city.addStation(TransportType.AVIA);
        city.setDistrict("NONE");
        city.setRegion("NONE");
        repository.save(city);
    }

    @Test
    void testGetCityByIdSuccess() {
        City city = new City("Magnitogorsk");
        city.addStation(TransportType.AVIA);
        city.setDistrict("NONE");
        city.setRegion("NONE");
        repository.save(city);

        City city1 = repository.findById(1);
        System.out.println(city1.getCreatedAt());
    }
}