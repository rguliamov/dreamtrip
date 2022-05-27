package com.github.rgulyamov.dreamtrip.app.diconfig;

import com.github.rguliamov.dreamtrip.app.hibernate.SessionFactoryBuilder;
import com.github.rguliamov.dreamtrip.app.repository.CityRepository;
import com.github.rguliamov.dreamtrip.app.repository.hibernate.HibernateCityRepository;
import com.github.rgulyamov.dreamtrip.app.dto.transform.Transformer;
import com.github.rgulyamov.dreamtrip.app.dto.transform.impl.TransformerImpl;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;
import com.github.rgulyamov.dreamtrip.app.service.impl.GeographicServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * Binds bean implementation and bean interface
 *
 * @author Guliamov Rustam
 */
public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(SessionFactoryBuilder.class).to(SessionFactoryBuilder.class).in(Singleton.class);
        bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
        bind(HibernateCityRepository.class).to(CityRepository.class).in(Singleton.class);
        bind(TransformerImpl.class).to(Transformer.class).in(Singleton.class);
    }
}
