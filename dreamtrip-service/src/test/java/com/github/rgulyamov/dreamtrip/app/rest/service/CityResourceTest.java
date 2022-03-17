package com.github.rgulyamov.dreamtrip.app.rest.service;

import com.github.rgulyamov.dreamtrip.app.rest.service.conf.JerseyConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Application;
import java.util.List;

/**
 * {@link CityResourceTest} is integration test that verifies {@link CityResource}
 *
 * @author Guliamov Rustam
 */
public class CityResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new JerseyConfig();
    }

    @Test
    public void testFindCitySuccess() {
        List<?> cityLit = target("cities").request().get(List.class);
        assertNotNull(cityLit);
        assertTrue(cityLit.contains("St. Petersburg"));
        assertTrue(cityLit.contains("Moscow"));
    }
}
