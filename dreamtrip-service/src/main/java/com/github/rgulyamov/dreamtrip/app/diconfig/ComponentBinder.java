package com.github.rgulyamov.dreamtrip.app.diconfig;

import com.github.rguliamov.dreamtrip.app.repository.CityRepository;
import com.github.rguliamov.dreamtrip.app.repository.inmemory.InMemoryCityRepository;
import com.github.rgulyamov.dreamtrip.app.dto.transform.Transformer;
import com.github.rgulyamov.dreamtrip.app.dto.transform.impl.TransformerImpl;
import com.github.rgulyamov.dreamtrip.app.service.GeographicService;
import com.github.rgulyamov.dreamtrip.app.service.impl.GeographicServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Binds bean implementation and bean interface
 *
 * @author Guliamov Rustam
 */
public class ComponentBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(GeographicServiceImpl.class).to(GeographicService.class);
        bind(InMemoryCityRepository.class).to(CityRepository.class);
        bind(TransformerImpl.class).to(Transformer.class);
    }
}
