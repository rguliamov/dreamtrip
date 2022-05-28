package com.github.rguliamov.dreamtrip.app.repository.hibernate;

import com.github.rguliamov.dreamtrip.app.hibernate.SessionFactoryBuilder;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Station;
import com.github.rguliamov.dreamtrip.app.model.search.criteria.StationCriteria;
import com.github.rguliamov.dreamtrip.app.repository.StationRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Guliamov Rustam
 */
public class HibernateStationRepository implements StationRepository {

    private SessionFactory sessionFactory;

    @Inject
    public HibernateStationRepository(SessionFactoryBuilder builder) {
        sessionFactory = builder.getSessionFactory();
    }

    @Override
    public List<Station> findAllByCriteria(StationCriteria criteria) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Station> criteriaQuery = criteriaBuilder.createQuery(Station.class);
            Root<Station> stationRoot =criteriaQuery.from(Station.class);

            criteriaQuery.select(stationRoot);
            if(criteria.getAddress() != null) {
                criteriaQuery.where(criteriaBuilder
                        .equal(stationRoot.get(Station.ADDRESS), criteria.getAddress()));
            }

            if(criteria.getTransportType() != null) {
                criteriaQuery.where(criteriaBuilder
                        .equal(stationRoot.get(Station.TRANSPORT_TYPE), criteria.getTransportType()));
            }

            if(!StringUtils.isEmpty(criteria.getCityName())) {
                Query<City> query = session.createQuery("from City c where c.name = :name1", City.class);
                List<City> result = query.setParameter("name1", criteria.getCityName()).getResultList();
                City city = result.get(0);

                criteriaQuery.where(criteriaBuilder
                        .equal(stationRoot.get(Station.CITY_NAME), city));
            }

            List<Station> stations = session.createQuery(criteriaQuery).list();

            transaction.commit();

            return stations;
        }
    }
}
