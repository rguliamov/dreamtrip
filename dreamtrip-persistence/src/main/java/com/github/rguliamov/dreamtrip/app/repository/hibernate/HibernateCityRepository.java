package com.github.rguliamov.dreamtrip.app.repository.hibernate;

import com.github.rguliamov.dreamtrip.app.hibernate.SessionFactoryBuilder;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.repository.CityRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Guliamov Rustam
 */
public class HibernateCityRepository implements CityRepository {
    private final SessionFactoryBuilder sessionFactoryBuilder;

    private final SessionFactory sessionFactory;

    @Inject
    public HibernateCityRepository(SessionFactoryBuilder sessionFactoryBuilder) {
        this.sessionFactoryBuilder = sessionFactoryBuilder;
        sessionFactory = sessionFactoryBuilder.getSessionFactory();
    }

    @Override
    public void save(City city) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(city);
            transaction.commit();
        }
    }

    @Override
    public City findById(int cityId) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(City.class, cityId);
        }
    }

    @Override
    public void delete(int cityId) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            City city = session.get(City.class, cityId);
            if(city != null) {
                session.remove(city);
            }
            transaction.commit();
        }
    }

    @Override
    public List<City> findAll() {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<City> cities = session.createQuery("from City", City.class).list();
            transaction.commit();

            return cities;
        }
    }
}
